package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import dao.ChamadoDAO;
import model.Chamado;
import model.Fila;

public class ChamadoService {
	ChamadoDAO dao;
	
	public ChamadoService(ChamadoDAO dao){
		this.dao = dao;
	}
	
	public int novoChamado(Chamado chamado) throws IOException{
		chamado.setDataAbertura(new Date());
		chamado.setDataFechamento(null);
		chamado.setStatus(Chamado.ABERTO);
		return -1;
		//return dao.inserirChamado(chamado);
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);
	}

}
