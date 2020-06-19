package imob.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import imob.Conexao.Conexao;
import imob.Pojo.Cliente;

public class ClienteDAO {
	private Connection con;
	
	public boolean adicionarCliente(Cliente cli) throws Exception {
		String sql_insert_cli = "INSERT INTO cliente(nome, rg, cpf) VALUES (?, ?, ?)";
		String sql_insert_tel = "INSERT INTO telefone_cli(cod_cli, telefone) VALUES (?, ?)";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = ((Connection) con).prepareStatement(sql_insert_cli, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, cli.getNome());
			stmt.setString(2, cli.getRg());
			stmt.setString(3, cli.getCpf());
			
			int rowsAffected = stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
	        if(rs.next()){
	            id = rs.getInt(1);
	        }
	        
	        stmt = ((Connection) con).prepareStatement(sql_insert_tel);
			
			stmt.setInt(1, id);
			stmt.setString(2, cli.getTelefone());
	        
			int rowsAffected2 = stmt.executeUpdate();
			
			stmt.close();
			if(rowsAffected > 0 && rowsAffected2 > 0)
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
		String sql = "SELECT c.*, t.telefone FROM cliente c INNER JOIN telefone_cli t ON c.cod=t.cod_cli;";
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
				String telefone = rs.getString("telefone");
				
				Cliente cli= new Cliente(cod, nome, rg, cpf, telefone);
				
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
	
	public boolean excluirCliente(String cpf) {
		String sql = "DELETE FROM cliente WHERE cpf = ?";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			
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
	
	public ArrayList<Cliente> getClienteById(String str) {
		String sql = "SELECT c.*, t.telefone FROM cliente c INNER JOIN "
				+ "telefone_cli t ON c.cod=t.cod_cli WHERE "
				+ "upper(c.nome) LIKE '%"+str+"%' ORDER BY c.nome";
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
				String telefone = rs.getString("telefone");
				
				Cliente cli= new Cliente(cod, nome, rg, cpf, telefone);
				
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
	
	public Cliente getClienteByCpf(String cpf) {
		String sql = "SELECT c.*, t.telefone FROM cliente c INNER JOIN "
				+ "telefone_cli t ON c.cod=t.cod_cli WHERE "
				+ "c.cpf = '?'";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int cod = rs.getInt("cod");
			String nome = rs.getString("nome");
			String rg = rs.getString("rg");
			String telefone = rs.getString("telefone");
			
			Cliente cli = new Cliente(cod, nome, rg, cpf, telefone);
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
