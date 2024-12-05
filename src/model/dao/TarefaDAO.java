package model.dao;

import java.util.List;
import model.entities.Tarefa;

public interface TarefaDao {
    void inserirTarefa(Tarefa tarefa);
    void deletarTarefa(int id);
    void atualizarTarefa(Tarefa tarefa);
    Tarefa buscarTarefa(int id);
    List<Tarefa> listarTarefas();
}
