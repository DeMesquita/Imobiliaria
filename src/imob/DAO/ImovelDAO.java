package imob.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import imob.Conexao.Conexao;
import imob.Pojo.Imovel;

public class ImovelDAO {
	private Connection con;
	
	public boolean adicionarImovel(Imovel imo) {
		String sql = "INSERT INTO imovel"
				+ "(cod_prop, cod_imob, area, numero, rua, bairro, cep, "
				+ "cidade, uf, pais, status, cozinha, quarto, areaserv, arealaz, "
				+ "vagagaragem, banheiros, sala) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = ((Connection) con).prepareStatement(sql);
			
			stmt.setInt(1, imo.getCod_prop());
			stmt.setInt(2, imo.getCod_imob());
			stmt.setFloat(3, imo.getArea());
			stmt.setString(4, imo.getNumero());
			stmt.setString(5, imo.getRua());
			stmt.setString(6, imo.getBairro());
			stmt.setString(7, imo.getCep());
			stmt.setString(8, imo.getCidade());
			stmt.setString(9, imo.getEstado());
			stmt.setString(10, imo.getPais());
			stmt.setBoolean(11, imo.isStatus());
			stmt.setBoolean(12, imo.isCozinha());
			stmt.setInt(13, imo.getQuarto());
			stmt.setBoolean(14, imo.isAreaServ());
			stmt.setBoolean(15, imo.isAreaLaz());
			stmt.setInt(16, imo.getVagaGaragem());
			stmt.setInt(17, imo.getBanheiros());
			stmt.setInt(18, imo.getSala());
			
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
	

	public ArrayList<Imovel> listarImoveis(){
		String sql = "SELECT * FROM imovel";
		ArrayList<Imovel> imoveis = new ArrayList<>();
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int cod = rs.getInt("cod");
				String nome = rs.getString("nome");
				String rg = rs.getString("rg");
				String cpf = rs.getString("cpf");
				
				Imovel imo = new Imovel(cod, nome, rg, cpf);
				
				imoveis.add(imo);
				
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
		
		return imoveis;
	}
	
	public boolean excluirImovel(int cod) {
		String sql = "DELETE FROM imovel WHERE cod = ?";
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
	
	public Imovel getImovelById(int id) {
		String sql = "SELECT * FROM imovel WHERE id = ?";
		this.con = new Conexao().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Imovel imo = new Imovel(id, rs.getString("nome"), rs.getString("rg"), rs.getString("cpf"));
			stmt.close();
			
			return imo;
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
