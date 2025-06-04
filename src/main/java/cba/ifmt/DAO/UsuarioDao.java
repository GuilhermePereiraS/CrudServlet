package cba.ifmt.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cba.ifmt.entidades.Municipio;
import cba.ifmt.entidades.Usuario;

public class UsuarioDao {
	Connection conexao = null;
	PreparedStatement pStmt = null;
	Statement stmt = null;
	
	public void adicionarUsuarioNoDb(Usuario usuario) throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/dbServlet",
					"postgres",
					"guigui2006@"
					);
			
			String sql = "CREATE TABLE IF NOT EXISTS usuarios (id SERIAL PRIMARY KEY, nome VARCHAR(100), cpf VARCHAR(100),email VARCHAR(100), municipio_id INT REFERENCES municipios(id))" ;
			stmt = conexao.createStatement();
			stmt.execute(sql);
			
			sql = "INSERT INTO usuarios (nome,email,cpf,municipio_id) value (?,?,?,?)";
			
			pStmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pStmt.setString(1, usuario.getNome());
			pStmt.setString(2, usuario.getEmail());
			pStmt.setString(3, usuario.getCpf());
			pStmt.setInt(4, usuario.getMunicipio().getId());
			
			pStmt.executeUpdate();
			
			System.out.println("Usuario adicionado ao banco de dados com sucesso");
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			if (!conexao.isClosed()) {
				conexao.close();
			}
			if (!pStmt.isClosed()) {
				pStmt.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
		}
	}
	
	public Usuario consultarUsuario(String nome) throws SQLException {
		Usuario usuario = new Usuario();
		Connection conexao = null;
		PreparedStatement pStmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jbdc:postgresql://localhost:5432/dbServlet", 
					"postgres", 
					"postgres"
					);
			String sql = "SELECT * FROM usuarios u WHERE u.nome = " + "'" + nome + "'";
			pStmt = conexao.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			MunicipioDao mDao = new MunicipioDao();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCpf(rs.getString("cpf"));
				Municipio municipio = new Municipio();
				
				for (Municipio m : mDao.listaMunicipio()) {
					if (m.getNome().equals(rs.getString("municipio"))) {
						municipio = m;
					}
				}
				
				usuario.setMunicipio(municipio);
				
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			 if (!conexao.isClosed()) {
				conexao.close();
			}
			if (!pStmt.isClosed()) {
				pStmt.close();
			}
		}
		return usuario;
	} 
	
	
	public void editarUsuario(Usuario u) throws SQLException {
		Connection conexao = null;
		PreparedStatement pStmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jbdc:postgresql://localhost:5432/dbServlet", 
					"postgres", 
					"postgres"
					);
			String sql = "UPDATE usuarios SET nome=?, email=?, cpf=?, municipio=? WHERE  id=?";
			pStmt = conexao.prepareStatement(sql);
			
			pStmt.setString(1, u.getNome());
			pStmt.setString(2, u.getEmail());
			pStmt.setString(3, u.getCpf());
			pStmt.setString(4, u.getMunicipio().getNome());
			pStmt.setInt(5,u.getId());
			
			pStmt.executeUpdate();
			
			
				
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			 if (!conexao.isClosed()) {
				conexao.close();
			}
			if (!pStmt.isClosed()) {
				pStmt.close();
			}
		}
	} 
	
	public List<Usuario> listarTodos() throws SQLException {
		List<Usuario> lista = new ArrayList();
		Connection conexao = null;
		PreparedStatement pStmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jbdc:postgresql://localhost:5432/dbServlet", 
					"postgres", 
					"postgres"
					);
			String sql = "SELECT * FROM usuarios";
			pStmt = conexao.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			MunicipioDao mDao = new MunicipioDao();
			
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setCpf(rs.getString("cpf"));
				Municipio municipio = new Municipio();
				
				for (Municipio m : mDao.listaMunicipio()) {
					if (m.getNome().equals(rs.getString("municipio"))) {
						municipio = m;
					}
				}
				
				usuario.setMunicipio(municipio);
				
				lista.add(usuario);
				
				// usuario.setMunicipio(rs.getString("municipio"));
				// arrumar uma forma de inserir um municipio da lista pelo nome
			}
			
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			 if (!conexao.isClosed()) {
				conexao.close();
			}
			if (!pStmt.isClosed()) {
				pStmt.close();
			}
		}
		return lista;
	} 
}