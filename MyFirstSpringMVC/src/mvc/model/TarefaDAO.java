package mvc.model;
import java.sql.*;
import java.util.*;

public class TarefaDAO {
	private Connection connection = null;

	public TarefaDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/meus_dados?useTimezone=true&serverTimezone=UTC", "root", "Gmm/d2cadu");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionaDescricao(Tarefa tarefa) {
		try {
			String sql = "INSERT INTO Tarefa (descricao) values(?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,tarefa.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {e.printStackTrace();}
	}

	public List<Tarefa> getLista() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tarefa");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {System.out.println(e);}
		return tarefas;
	}
	public void remove(Tarefa tarefa) {
		try {
			PreparedStatement stmt =
					connection.prepareStatement("DELETE FROM Tarefa WHERE id=?");
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {System.out.println(e);}
	}
	public Tarefa buscaPorId(Long id) {
		Tarefa tarefa = new Tarefa();
		try {
			PreparedStatement stmt =
					connection.prepareStatement("SELECT * FROM Tarefa WHERE id=? ");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {System.out.println(e);}
		return tarefa;
	}
	public void close() {
		try { connection.close();}
		catch (SQLException e) {e.printStackTrace();}
	}
}