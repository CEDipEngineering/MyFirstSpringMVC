package br.edu.insper.CRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mvc.model.Task;
import mvc.model.User;

public class DAO {
	private Connection connection;
	public DAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/mm?useTimezone=true&serverTimezone=UTC", "root", "Gmm/d2cadu");
	}
	
	public List<Task> getListTask(String search, String sessId) throws SQLException{
		List<Task> out = new ArrayList<>();
		String sql = "SELECT * FROM Task";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			if(rs.getString("userId").equals(sessId)) {		
				if((rs.getString("Title") + rs.getString("Description")).toLowerCase().contains(search.toLowerCase())) {
					Task task = new Task(rs.getInt("Id"));
					task.setTitle(rs.getString("Title"));
					task.setDescription(rs.getString("Description"));
					task.setDate(rs.getString("Deadline"));
					out.add(task);	
					}
				}
			}
		
		stmt.close();
		rs.close();
		
		return out;
	}
	
	public void modifyEntry(int Id, Task newEntry) throws SQLException {
		
		String sql ="UPDATE Task SET " + 
					"Title = \"" + newEntry.getTitle() +
					"\", Description = \"" + newEntry.getDescription() +
					"\", Deadline = \"" + newEntry.getDate() + 
					"\" WHERE Id = " + String.valueOf(Id) + ";";
		System.out.println(sql);		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
		}
	
	public void addEntry(Task newEntry) throws SQLException {
		String sql ="INSERT Task (Title, Description, userId, Deadline) VALUES (\"" + 
		newEntry.getTitle() + "\", \"" + 
		newEntry.getDescription() + "\", \""+
		newEntry.getUserId() + "\", \""+
		newEntry.getDate() + "\");";
		System.out.println(sql);		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
		
	}
	
	public void removeEntry(int Id) throws SQLException {
		String sql ="DELETE FROM Task WHERE Id = " + String.valueOf(Id) + ";";
		System.out.println(sql);		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
		}
	
	public List<Integer> getIdList(String sessId) throws SQLException {
		List<Integer> out = new ArrayList<>();
		String sql = "SELECT * FROM Task";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			if (rs.getString("userId").equals(sessId)) {
				out.add(rs.getInt("Id"));
			}
		}
		stmt.close();
		rs.close();
		
		return out;
	}
	
	public Task getTask(int Id) throws SQLException {
		String sql = "SELECT * FROM Task WHERE Id = "+String.valueOf(Id);
		System.out.println(sql);
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		Task out = new Task(rs.getInt("Id"));
		out.setTitle(rs.getString("Title"));
		out.setDate(rs.getString("Deadline"));
		out.setDescription(rs.getString("Description"));
		out.setUserId(rs.getString("userId"));

		
		stmt.close();
		rs.close();
		
		return out;
	}
	
	
	public List<User> getAllUsers() throws SQLException {
		List<User> out = new ArrayList<>();
		String sql = "SELECT * FROM User";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPasswd(rs.getString("passwd"));
			user.setUserId(Integer.parseInt(rs.getString("userId")));
			out.add(user);
		}
		stmt.close();
		rs.close();
		return out;

	}
	
	public void addUser(User newUser) throws SQLException {
		String sql ="INSERT User (username, passwd) VALUES (\"" +  
		newUser.getUsername() + "\", \""+
		newUser.getPasswd() + "\");";
		System.out.println(sql);		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public boolean isValidUsername(User user) throws SQLException {
		String sql = "SELECT username FROM User";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			if(user.getUsername() == rs.getString("username")) {
				stmt.close();
				rs.close();
				return false;
			}
		}
		stmt.close();
		rs.close();
		return true;
	}
	
	public boolean isValidPassword(User user) throws SQLException {
		String sql = "SELECT passwd FROM User";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			if(user.getPasswd() == rs.getString("passwd")) {
				stmt.close();
				rs.close();
				return false;
			}
		}
		stmt.close();
		rs.close();
		return true;
	}
	
	public int getUserId(User user) throws SQLException {
		String sql = "SELECT * FROM User";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println(user.getPasswd() + " " + rs.getString("passwd"));
			System.out.println(user.getUsername() + " " + rs.getString("username"));
			if(user.getPasswd().equals(rs.getString("passwd")) && user.getUsername().equals(rs.getString("username"))) {
				System.out.println("User found!");
				user.setUserId(rs.getInt("userId"));
				stmt.close();
				rs.close();
				return user.getUserId();
			}
		}
		System.out.println("User not found!");
		stmt.close();
		rs.close();
		return -1;		
	}
}
