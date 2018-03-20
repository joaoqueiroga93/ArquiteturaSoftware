package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import model.Login;

public class LoginDAO {

    private Connection conn;
	
	@Autowired
	public LoginDAO(DataSource dataSource) throws IOException{
	try{
	this.conn = dataSource.getConnection();
	} catch (SQLException e){
	throw new IOException(e);
	}
	}
	
	public boolean validaUsuario(Login login) throws IOException{
		boolean flValidacao = false;
		String query = "select senha "+
				"from login where usuario = ?";
		
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setString(1, login.getUsuario());
			
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()){
					
					flValidacao = true;
			
				}
			} catch(SQLException e){
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		return flValidacao;
	}

}
