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
	//Não posso criar um metodo para adicionar um municipio pelo dao por que não quero que um usuario possa adicionar um municipio no front-end, e não quero um textao com municipios no dao 
	
	
	public List<Municipio> listaMunicipio() throws SQLException {
		List<Municipio> lista = new ArrayList();

		Connection conexao = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		conexao = DriverManager.getConnection(
				"jbdc:postgresql://localhost:5432/dbServlet", //url
				"postgres", //usuario
				"postgres" //senha
				);
		
		String sql = "SELECT * FROM municipios";
		stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
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
			 if (!conexao.isClosed()) {
				conexao.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
		}
		return lista;
	} 
}
