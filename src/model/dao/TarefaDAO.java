package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DbException;
import model.entities.Tarefa;

public class TarefaDAO {
	
	private Connection conn;

	public TarefaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void inserirTarefa(Tarefa tarefa) {
	    String sql = "INSERT INTO Tarefa (titulo, descricao, status, data_vencimento) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement st = conn.prepareStatement(sql)) {
	        st.setString(1, tarefa.getTitulo());
	        st.setString(2, tarefa.getDescricao());
	        st.setString(3, tarefa.getStatus());
	        st.setDate(4, Date.valueOf(tarefa.getDataVencimento()));
	        st.executeUpdate();
	    }
	    catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

	public void deletarTarefa(int id) {
		String sql = "DELETE FROM Tarefa WHERE id = ?";
		try(PreparedStatement st = conn.prepareStatement(sql)){
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}
	
	
}
