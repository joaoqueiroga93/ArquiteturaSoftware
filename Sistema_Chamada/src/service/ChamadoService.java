package service;

import java.io.IOException;

import model.Chamado;
import dao.ChamadoDAO;


public class ChamadoService {
	ChamadoDAO dao = new ChamadoDAO();
	
	public int criar(Chamado chamado) throws IOException {
		return dao.criar(chamado);
	}
	
	public void atualizar(Chamado chamado) throws IOException {
		dao.atualizar(chamado);
	}
	
	public void excluir(int id) throws IOException {
		dao.excluir(id);
	}
	
	public Chamado carregar(int id) throws IOException {
		return dao.carregar(id);
	}

}