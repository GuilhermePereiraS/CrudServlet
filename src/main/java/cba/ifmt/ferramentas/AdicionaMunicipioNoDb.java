package cba.ifmt.ferramentas;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class AdicionaMunicipioNoDb {
	public static void main (String args[]) throws SQLException {
		Connection conexao = null;
		PreparedStatement stmt = null;
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName("org.postgresql.Driver");
			LinkedList<String> municipiosUsuario = new LinkedList<>();
			boolean sair = false;
			
			System.out.println("Para sair escreva 'sair' ");
			do {
				System.out.println("Insira o nome do municipio a ser adicionado: ");
				String resposta = scan.nextLine();
				if (resposta.equals("sair")) {
					sair = true;
				} else {
					municipiosUsuario.add(resposta);
				}
			} while (sair=false);
			
			 conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/dbServlet",
					"postgres",
					"postgres"
					);
			 
			 String sql = "INSER INTO municipios (nome) values (?)";
			 stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			 
			 for (String municipio : municipiosUsuario) {
				 stmt.setString(1, municipio);
				 stmt.addBatch();
			 }
			 
			 stmt.executeBatch();
			 
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			 if (!conexao.isClosed()) {
				conexao.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
		}
	}
}
