package imob.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import imob.Conexao.Conexao;
import imob.Pojo.Cliente;

public class AluguelDAO {
	private Connection con;
	
	public boolean adicionarCliente(Cliente cli) {
		String sql = "INSERT INTO cliente( nome, rg, cpf) VALUES ( ?, ?, ?)";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = ((Connection) con).prepareStatement(sql);
			
			stmt.setString(1, cli.getNome());
			stmt.setString(2, cli.getRg());
			stmt.setString(3, cli.getCpf());
			
			int rowsAffected = stmt.executeUpdate();
			stmt.close();
			if(rowsAffected > 0)
				return true;
			
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		finally {
			try {
				this.con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

	public ArrayList<Cliente> listarClientes(){
		String sql = "SELECT * FROM cliente";
		ArrayList<Cliente> clientes = new ArrayList<>();
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int cod = rs.getInt("cod");
				String nome = rs.getString("nome");
				String rg = rs.getString("rg");
				String cpf = rs.getString("cpf");
				
				Cliente cli= new Cliente(cod, nome, rg, cpf);
				
				clientes.add(cli);
				
			}
			
			stmt.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		finally {
			try {
				this.con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return clientes;
	}
	
	public boolean excluirCliente(int cod) {
		String sql = "DELETE FROM cliente WHERE cod = ?";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, cod);
			
			int affectedRows = stmt.executeUpdate();
			stmt.close();
			
			if(affectedRows > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Cliente getClienteById(int id) {
		String sql = "SELECT * FROM usuario WHERE id = ?";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Cliente cli = new Cliente(id, rs.getString("nome"), rs.getString("rg"), rs.getString("cpf"));
			stmt.close();
			
			return cli;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
