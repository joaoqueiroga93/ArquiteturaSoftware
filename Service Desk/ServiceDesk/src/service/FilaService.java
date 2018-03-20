package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.FilaDAO;
import model.Fila;

public class FilaService {
	private FilaDAO dao;
	
	public FilaService(FilaDAO dao) {
		this.dao = dao;
	}
	public ArrayList<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	public Fila carregar(int id) throws IOException{
		return dao.carregar(id);
	}
}
