package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Fila;

public class FilaDAO {
	public int criar(Fila fila) throws IOException {
		String sqlInsert = "INSERT INTO fila(nm_fila) VALUES (?, ?, ?)";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setString(1, fila.getNome());
				
				stm.execute();
				String sqlQuery = "SELECT LAST_INSERT_ID()";
				try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
						ResultSet rs = stm2.executeQuery();) {
					if (rs.next()) {
						fila.setId(rs.getInt(1));
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
		return fila.getId();
	}

	public void atualizar(Fila fila) throws IOException {
		String sqlUpdate = "UPDATE fila SET nm_fila=? WHERE id=?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
				stm.setString(1, fila.getNome());
				
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

	public Fila carregar(int id) throws IOException {
		Fila fila = new Fila();
		fila.setId(id);
		String sqlSelect = "SELECT nm_fila FROM fila WHERE fila.id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, fila.getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						fila.setNome(rs.getString("nome"));
						
					} else {
						fila.setId(-1);
						fila.setNome(null);
					
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
		return fila;
	}

	public ArrayList<Fila> listarFila() throws IOException {
		Fila fila;
		ArrayList<Fila> lista = new ArrayList<>();
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			String sqlSelect = "SELECT id, nm_fla FROM chamado";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						fila = new Fila();
						fila.setId(rs.getInt("id"));
						fila.setNome(rs.getString("nome"));
						lista.add(fila);
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

	public ArrayList<Fila> listarFila(String chave) throws IOException {
		Fila fila;
		ArrayList<Fila> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nm_fila FROM chamado where upper(nome) like ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						fila = new Fila();
						fila.setId(rs.getInt("id"));
						fila.setNome(rs.getString("nome"));
						lista.add(fila);
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

