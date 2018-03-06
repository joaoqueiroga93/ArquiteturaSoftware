package service;

import java.io.IOException;
import java.util.ArrayList;

import model.Fila;
import dao.FilaDAO;

public class FilaService {
	FilaDAO dao;
	
	public FilaService(){
		dao = new FilaDAO();
	}
	public ArrayList<Fila> listarClientes() throws IOException {
		return dao.listarFila();
	}
	public ArrayList<Fila> listarFila(String chave) throws IOException {
		return dao.listarFila(chave);
	}

}
