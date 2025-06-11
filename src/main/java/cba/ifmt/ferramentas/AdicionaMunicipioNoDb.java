package cba.ifmt.ferramentas;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdicionaMunicipioNoDb {
	public static void main (String args[]) throws SQLException {
		Connection conexao = null;
		PreparedStatement pStmt = null;
		Scanner scan = new Scanner(System.in);
		String sql = null;
		try {
			
			Class.forName("org.postgresql.Driver");
			List<String> municipiosUsuario = new ArrayList<>();
			
			boolean sair = false;
			
			System.out.println("Para sair escreva 'sair' ");
			do {
				System.out.println("Insira o nome do municipio a ser adicionado: ");
				String resposta = scan.nextLine();
				if (resposta.toLowerCase().equals("sair")) {
					sair = true;
				} else {
					municipiosUsuario.add(resposta);
				}
			} while (sair==false);
			
			 conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/dbServlet",
					"postgres",
					"guigui2006@"
					);
			 
			 sql = "CREATE TABLE IF NOT EXISTS municipios (id SERIAL PRIMARY KEY, nome VARCHAR(30))" ;
			 Statement stmt = conexao.createStatement();
			 stmt.execute(sql);
			 
			 sql = "INSERT INTO municipios (nome) values (?)";
			 pStmt = conexao.prepareStatement(sql);
			 
			 for (String municipio : municipiosUsuario) {
				 pStmt.setString(1, municipio);
				 pStmt.addBatch();
			 }
			 
			 pStmt.executeBatch();
			 
			 System.out.println("Municipios adicionados com sucesso");
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			 if ( conexao != null && !conexao.isClosed() ) {
				conexao.close();
			}
			if ( pStmt != null && !pStmt.isClosed()) {
				pStmt.close();
			}
		}
	}
}
//