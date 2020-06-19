package imob.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {	
		private final String ip = "localhost";
		private final Integer port = 5432;
		private final String user = "postgres";
		private final String password = "postgres";
		private final String database = "imobiliaria";
		
		public Connection getConnection() {
			try {
				System.out.println("Conectado");
				return DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+database, user, password);
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
