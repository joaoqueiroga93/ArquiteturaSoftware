package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Chamado;

public class ChamadoDAO {
	public int criar(Chamado chamado) throws IOException {
		String sqlInsert = "INSERT INTO chamado(descricao, status, dt_abertura, dt_fechamento) VALUES (?, ?, ?)";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setString(1, chamado.getDescricao());
				stm.setString(2, chamado.getStatus());
				stm.setInt(3, chamado.getDt_abertura());
				stm.setInt(4, chamado.getDt_fechamento());
				stm.execute();
				String sqlQuery = "SELECT LAST_INSERT_ID()";
				try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
						ResultSet rs = stm2.executeQuery();) {
					if (rs.next()) {
						chamado.setId(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return chamado.getId();
	}

	public void atualizar(Chamado chamado) throws IOException {
		String sqlUpdate = "UPDATE chamado SET descricao=?, status=?, dt_abertura=?, dt_fechamento=? WHERE id=?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
				stm.setString(1, chamado.getDescricao());
				stm.setString(2, chamado.getStatus());
				stm.setInt(3, chamado.getDt_abertura());
				stm.setInt(4, chamado.getDt_fechamento());
				stm.execute();
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
	}

	public void excluir(int id) throws IOException {
		String sqlDelete = "DELETE FROM chamado WHERE id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
				stm.setInt(1, id);
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
	}

	public Chamado carregar(int id) throws IOException {
		Chamado chamado = new Chamado();
		chamado.setId(id);
		String sqlSelect = "SELECT descricao, status, dt_abertura, dt_fechamento FROM chamado WHERE chamado.id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, chamado.getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						chamado.setDescricao(rs.getString("descricao"));
						chamado.setStatus(rs.getString("status"));
						chamado.setDt_abertura(rs.getInt("dt_abertura"));
						chamado.setDt_fechamento(rs.getInt("dt_fechamento"));
					} else {
						chamado.setId(-1);
						chamado.setDescricao(null);
						chamado.setStatus(null);
						chamado.setDt_abertura(0);
						chamado.setDt_fechamento(0);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return chamado;
	}

	public ArrayList<Chamado> listarChamado() throws IOException {
		Chamado chamado;
		ArrayList<Chamado> lista = new ArrayList<>();
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			String sqlSelect = "SELECT id, descricao, status, dt_abertura, dt_fechamento FROM chamado";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						chamado = new Chamado();
						chamado.setId(rs.getInt("id"));
						chamado.setDescricao(rs.getString("descricao"));
						chamado.setStatus(rs.getString("status"));
						chamado.setDt_abertura(rs.getInt("Dt_abertura"));
						chamado.setDt_fechamento(rs.getInt("Dt_fechamento"));
						lista.add(chamado);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return lista;
	}

	public ArrayList<Chamado> listarChamado(String chave) throws IOException {
		Chamado chamado;
		ArrayList<Chamado> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, descricao, status, dt_abertura, dt_fechamento FROM chamado where upper(nome) like ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						chamado = new Chamado();
						chamado.setId(rs.getInt("id"));
						chamado.setDescricao(rs.getString("descricao"));
						chamado.setStatus(rs.getString("status"));
						chamado.setDt_abertura(rs.getInt("dt_abertura"));
						chamado.setDt_abertura(rs.getInt("dt_fechamento"));
						lista.add(chamado);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return lista;
	}
}
