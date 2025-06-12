package cba.ifmt.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cba.ifmt.entidades.Municipio;

public class MunicipioDao {
	
	
	public List<Municipio> listaMunicipio() throws SQLException {
		List<Municipio> lista = new ArrayList<>();

		Connection conexao = null;
		PreparedStatement pStmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		conexao = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/dbServlet",
				"postgres",
				"guigui2006@"
				);
		
		
		String sql = "SELECT * FROM municipios";
		pStmt = conexao.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		while (rs.next()) {
			Municipio municipio = new Municipio();
			municipio.setId(rs.getInt("id"));
			municipio.setNome(rs.getString("nome"));
			lista.add(municipio);
		}
		
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			if (pStmt != null) {
				if (!pStmt.isClosed()) {
					pStmt.close();
				}
				if (conexao != null) {
					if (!conexao.isClosed()) {
						conexao.close();			
					}
				}
				
			}
		}
		return lista;
	} 
}
