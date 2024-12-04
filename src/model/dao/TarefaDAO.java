package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public void deletarTarefa(int id) {
		String sql = "DELETE FROM Tarefa WHERE id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public Tarefa buscarTarefa(int id) {
		String sql = "SELECT * FROM Tarefa WHERE id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String titulo = rs.getString("titulo");
				String descricao = rs.getString("descricao");
				String status = rs.getString("status");
				LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();

				return new Tarefa(id, titulo, descricao, status, dataVencimento);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return null;
	}

	public List<Tarefa> listarTarefas() {
		List<Tarefa> tarefas = new ArrayList<>();
		String sql = "SELECT * FROM Tarefa";
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				Tarefa tarefa = new Tarefa(
						rs.getInt("id"), 
						rs.getString("titulo"), 
						rs.getString("descricao"),
						rs.getString("status"), 
						rs.getDate("data_vencimento").toLocalDate());
				tarefas.add(tarefa);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return tarefas;
	}

}
