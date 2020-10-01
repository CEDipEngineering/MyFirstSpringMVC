package br.edu.insper.CRUD.Controller;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.insper.CRUD.DAO;
import mvc.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isValidUsername = true; 
	private boolean isValidPassword = true;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
		
		request.setAttribute("validUser", this.isValidUsername);
		request.setAttribute("validPassword", this.isValidPassword);
		
		rd.forward(request, response);
		System.out.println("Fim doGet Login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Login");
		try {
			DAO dao = new DAO();

		String login, register;
		login = request.getParameter("login");
		register = request.getParameter("register");
		
		User offeredUser = new User();
		try {
			offeredUser.setUsername(Encrypt(request.getParameter("username")));
			offeredUser.setPasswd(Encrypt(request.getParameter("password")));
//			this.isValidPassword = dao.isValidPassword(offeredUser);
//			this.isValidUsername = dao.isValidUsername(offeredUser);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(login != null) {
			if(!authenticateUser(offeredUser)) {
				System.out.println("Auth Failed");
				doGet(request,response);
				return;
			} else {
				System.out.println("Auth Success");
				int id = dao.getUserId(offeredUser);
				System.out.println(id);
				if(id>=0) {
					String sessionId = String.valueOf(id);
					HttpSession session = request.getSession();
			      	session.setAttribute("sessionId", sessionId);
			      	response.sendRedirect(request.getContextPath() + "/TaskManager");
					return;
				}
			}
		} else if (register != null) {
			this.isValidPassword = dao.isValidPassword(offeredUser);
			this.isValidUsername = dao.isValidUsername(offeredUser);
			if (isValidUsername && isValidPassword) {
				dao.addUser(offeredUser);
				int id = dao.getUserId(offeredUser);
				if(id>0) {
					String sessionId = String.valueOf(id);
					HttpSession session = request.getSession();
			      	session.setAttribute("sessionId", sessionId);
			      	response.sendRedirect(request.getContextPath() + "/TaskManager");
					return;
				}
			}
		}
		
		
		
		System.out.println("fim doPost Login");
		doGet(request, response);
		
		
		
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String Encrypt(String info) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(
		  info.getBytes(StandardCharsets.UTF_8));
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	private boolean authenticateUser(User user) {
		try {
			DAO dao = new DAO();
			
			ArrayList<User> allUsers = (ArrayList<User>) dao.getAllUsers();
			for (User known: allUsers) {
				if (user.getUsername().equals(known.getUsername()) && user.getPasswd().equals(known.getPasswd())) {
					return true;
				}
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
		
}
