package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.TarefaDao;
import model.entities.Tarefa;

public class TarefaDaoJDBC implements TarefaDao {
    private Connection conn;

    public TarefaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserirTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO Tarefa (titulo, descricao, status, data_vencimento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, tarefa.getTitulo());
            st.setString(2, tarefa.getDescricao());
            st.setString(3, tarefa.getStatus());
            st.setDate(4, Date.valueOf(tarefa.getDataVencimento()));
            st.executeUpdate();
            System.out.println("Tarefa inserida com sucesso!");
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    @Override
    public void deletarTarefa(int id) {
        String sql = "DELETE FROM Tarefa WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Tarefa excluída com sucesso!");
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar tarefa: " + e.getMessage());
        }
    }

    @Override
    public void atualizarTarefa(Tarefa tarefa) {
        String sql = "UPDATE Tarefa SET titulo = ?, descricao = ?, status = ?, data_vencimento = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, tarefa.getTitulo());
            st.setString(2, tarefa.getDescricao());
            st.setString(3, tarefa.getStatus());
            st.setDate(4, Date.valueOf(tarefa.getDataVencimento()));
            st.setInt(5, tarefa.getId());
            st.executeUpdate();
            System.out.println("Tarefa atualizada com sucesso!");
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    @Override
    public Tarefa buscarTarefa(int id) {
        String sql = "SELECT * FROM Tarefa WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Tarefa(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("status"),
                    rs.getDate("data_vencimento").toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar tarefa: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM Tarefa";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                tarefas.add(new Tarefa(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("status"),
                    rs.getDate("data_vencimento").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao listar tarefas: " + e.getMessage());
        }
        return tarefas;
    }
}
