package model;

public class Chamado {
	
	private int id;
	private String descricao;
	private String status;
	private int dt_abertura;
	private int dt_fechamento;
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getDt_abertura() {
		return dt_abertura;
	}
	public void setDt_abertura(int dt_abertura) {
		this.dt_abertura = dt_abertura;
	}
	public int getDt_fechamento() {
		return dt_fechamento;
	}
	public void setDt_fechamento(int dt_fechamento) {
		this.dt_fechamento = dt_fechamento;
	}
	

}
