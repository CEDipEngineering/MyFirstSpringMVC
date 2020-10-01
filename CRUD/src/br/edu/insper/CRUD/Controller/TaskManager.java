package br.edu.insper.CRUD.Controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.insper.CRUD.DAO;
import mvc.model.Task;

/**
 * Servlet implementation class TaskManager
 */
@WebServlet("/TaskManager")
public class TaskManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sessionId;

    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public TaskManager() throws ClassNotFoundException, SQLException {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Tmgr");
		// TODO Auto-generated method stub
		// Just some basic html to know if it's working.
		
		// Let's validate the session first
		HttpSession session = request.getSession();
		this.sessionId = (String) session.getAttribute("sessionId");
		if (sessionId == null) {
			RequestDispatcher rd = request.getRequestDispatcher("Login");
			rd.forward(request, response);
		}
		
		
		
		
		DAO dao;
		try {
			dao = new DAO();
			String search = request.getParameter("search");
			if (search == null) {
				search = "";
			}
			request.setAttribute("search", search);
			List<Task> tasks = dao.getListTask(search, sessionId);
			request.setAttribute("tasks", tasks);
			request.setAttribute("tasksSize", tasks.size());
				
			RequestDispatcher rd = request.getRequestDispatcher("View/TaskManager.jsp");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Tmgr");
		
		HttpSession session = request.getSession();
		this.sessionId = (String) session.getAttribute("sessionId");
		if (sessionId == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
		}
		
		if (request.getParameter("logout") != null) {
			sessionId = null;
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		try {
			DAO dao = new DAO();
			String newTitle, newDesc, newDate, sReadId;
			List<Integer> idList = dao.getIdList(sessionId);
			ArrayList<String> mod = new ArrayList<String>();
			ArrayList<String> del = new ArrayList<String>();
			String add = request.getParameter("Add");
			String moddedMessage = request.getParameter("moddedMessage");
			
			for(int i : idList) {
				mod.add(request.getParameter("Mod" + String.valueOf(i)));
				del.add(request.getParameter("Del" + String.valueOf(i)));
			}
			System.out.println("ModList is: " + mod.toString());
			System.out.println("DelList is: " + del.toString());
			newTitle = request.getParameter("Title");
			newDesc = request.getParameter("Description");
			newDate = request.getParameter("Deadline");
			sReadId = request.getParameter("taskId");
			
			if(moddedMessage != null) {
				int readId = Integer.parseInt(sReadId);
				Task tsk = new Task(readId);
				tsk.setDate(newDate);
				tsk.setTitle(newTitle);
				tsk.setDescription(newDesc);
				tsk.setUserId(sessionId);
				dao.modifyEntry(readId, tsk);
				doGet(request, response);
				return;
			}
			
			if(add != null && newTitle != null && newDesc != null) {
				Task newTask = new Task(-1);
				newTask.setDate(newDate);
				newTask.setTitle(newTitle);
				newTask.setDescription(newDesc);
				newTask.setUserId(sessionId);
				dao.addEntry(newTask);
				doGet(request, response);
				return;
			}
			int idxMod = this.notNullIndex(mod);
			System.out.println(idxMod);
			if(idxMod >= 0) {
				modPage(dao.getTask(idList.get(idxMod)), request, response);
				return;
			}
			
			int idxDel = this.notNullIndex(del);
			System.out.println(idxDel);
			if(idxDel>=0) {
				dao.removeEntry(idList.get(idxDel));
				doGet(request, response);
				return;
			}
			
			doGet(request, response);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int notNullIndex(ArrayList<String> vec) {
		for(int i=0;i<vec.size();i++) {
			if(vec.get(i) != null) {
				return i;
			}
		}
		return -1;
	}
	
	public void modPage(Task tsk, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tsk", tsk);
		RequestDispatcher rd = request.getRequestDispatcher("View/ModPage.jsp");
		rd.forward(request, response);
	}
}
