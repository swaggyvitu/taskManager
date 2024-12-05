package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.TarefaDao;
import model.entities.Tarefa;

public class Program {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            TarefaDao dao = DaoFactory.createTarefaDao();

            while (true) {
                System.out.println("1. Inserir Tarefa");
                System.out.println("2. Atualizar Tarefa");
                System.out.println("3. Deletar Tarefa");
                System.out.println("4. Listar Tarefas");
                System.out.println("5. Buscar Tarefa");
                System.out.println("6. Sair");

                System.out.print("\nEscolha uma opção: ");
                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Título: ");
                        String titulo = sc.nextLine();
                        System.out.println("Descrição: ");
                        String descricao = sc.nextLine();
                        System.out.println("Status (PENDENTE, EM ANDAMENTO, CONCLUIDA): ");
                        String status = sc.nextLine();
                        System.out.println("Data de Vencimento (YYYY-MM-DD): ");
                        LocalDate dataVencimento = LocalDate.parse(sc.nextLine());
                        Tarefa novaTarefa = new Tarefa(titulo, descricao, status, dataVencimento);
                        dao.inserirTarefa(novaTarefa);
                        break;

                    case 2:
                        System.out.println("ID da Tarefa a atualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Tarefa tarefaAtualizar = dao.buscarTarefa(id);
                        if (tarefaAtualizar != null) {
                            System.out.println("Novo Título: ");
                            tarefaAtualizar.setTitulo(sc.nextLine());
                            System.out.println("Nova Descrição: ");
                            tarefaAtualizar.setDescricao(sc.nextLine());
                            System.out.println("Novo Status (PENDENTE, EM ANDAMENTO, CONCLUIDA): ");
                            tarefaAtualizar.setStatus(sc.nextLine());
                            System.out.println("Nova Data de Vencimento (YYYY-MM-DD): ");
                            tarefaAtualizar.setDataVencimento(LocalDate.parse(sc.nextLine()));
                            dao.atualizarTarefa(tarefaAtualizar);
                        } else {
                            System.out.println("Tarefa não encontrada.");
                        }
                        break;

                    case 3:
                        System.out.println("ID da Tarefa a deletar: ");
                        int idDeletar = sc.nextInt();
                        dao.deletarTarefa(idDeletar);
                        break;

                    case 4:
                        List<Tarefa> tarefas = dao.listarTarefas();
                        tarefas.forEach(System.out::println);
                        break;

                    case 5:
                        System.out.println("ID da Tarefa: ");
                        int idBuscar = sc.nextInt();
                        Tarefa tarefa = dao.buscarTarefa(idBuscar);
                        System.out.println(tarefa != null ? tarefa : "Tarefa não encontrada.");
                        break;

                    case 6:
                        System.out.println("Encerrando...");
                        return;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } catch (Exception e) {
            throw new DbException("Erro: " + e.getMessage());
        }
    }
}
