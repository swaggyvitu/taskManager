package model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {
	
	private int id;
	private String titulo;
	private String descricao;
	private String status;
	private LocalDate dataVencimento;
	
	public Tarefa() {
	}

	public Tarefa(int id, String titulo, String descricao, String status, LocalDate dataVencimento) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.dataVencimento = dataVencimento;
	}
	
	public Tarefa(String titulo, String descricao, String status, LocalDate dataVencimento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataVencimento = dataVencimento;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", status=" + status
				+ ", dataVencimento=" + dataVencimento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return id == other.id;
	}
	
	
	
}
