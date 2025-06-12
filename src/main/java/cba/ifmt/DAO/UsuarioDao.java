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
	
	
	public void deletarUsuarioNoDb(Usuario usuario) throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/dbServlet",
					"postgres",
					"guigui2006@"
					);
			String sql = "DELETE FROM usuarios WHERE id = ?";
			pStmt = conexao.prepareStatement(sql);
			pStmt.setInt(1, usuario.getId());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pStmt != null) {
				if (!pStmt.isClosed()) {
					pStmt.close();
				}
			} if (conexao != null) {
				if (!conexao.isClosed()) {
					conexao.close();
				}
			} 
		}
	}
	
	public void adicionarUsuarioNoDb(Usuario usuario) throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/dbServlet",
					"postgres",
					"guigui2006@"
					);
			
			String sql = "CREATE TABLE IF NOT EXISTS usuarios (id SERIAL PRIMARY KEY, nome VARCHAR(100), cpf VARCHAR(100),email VARCHAR(100), municipio_id INT REFERENCES municipios(id))";
			stmt = conexao.createStatement();
			stmt.execute(sql); 
			
			sql = "INSERT INTO usuarios (nome,email,cpf,municipio_id) values (?,?,?,?)";
			
			pStmt = conexao.prepareStatement(sql);
			
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
			if (pStmt != null) {
				if (!pStmt.isClosed()) {
					pStmt.close();
				}
			} if (stmt != null) {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			}if (conexao != null) {
				if (!conexao.isClosed()) {
					conexao.close();
				}
			} 
		}
	}
	
	public Usuario consultarUsuario(String nome) throws SQLException {
		Usuario usuario = new Usuario();
		
			try {
				Class.forName("org.postgresql.Driver");
				conexao = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/dbServlet", 
						"postgres", 
						"guigui2006@"
						);
				String sql = "SELECT * FROM usuarios u WHERE u.nome = ?";
				pStmt = conexao.prepareStatement(sql);
				pStmt.setString(1,nome);
				ResultSet rs = pStmt.executeQuery();
			
				if (rs.next()) {
					MunicipioDao mDao = new MunicipioDao();
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setCpf(rs.getString("cpf"));
					Municipio municipio = new Municipio();
					
					for (Municipio m : mDao.listaMunicipio()) {
						if (m.getId() == rs.getInt("municipio_id")) {
							municipio = m;
						}
					}
					
					usuario.setMunicipio(municipio);
					
				}
				
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (pStmt != null) {
				if (!pStmt.isClosed()) {
					pStmt.close();
				}
			} if (conexao != null) {
				if (!conexao.isClosed()) {
					conexao.close();
				}
			} 
		}
		return usuario;
	} 
	
	
	public void editarUsuario(Usuario u) throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/dbServlet", 
					"postgres", 
					"guigui2006@"
					);
			String sql = "UPDATE usuarios SET nome=?, email=?, cpf=?, municipio_id=? WHERE  id=?";
			pStmt = conexao.prepareStatement(sql);
			
			pStmt.setString(1, u.getNome());
			pStmt.setString(2, u.getEmail());
			pStmt.setString(3, u.getCpf());
			pStmt.setInt(4, u.getMunicipio().getId());
			pStmt.setInt(5,u.getId());
			
			pStmt.executeUpdate();
			
			
				
		} catch (ClassNotFoundException e) {
			System.err.println("Deu ruim com o driver sql: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		} finally {
			if (pStmt != null) {
				if (!pStmt.isClosed()) {
					pStmt.close();
				}
			} if (conexao != null) {
				if (!conexao.isClosed()) {
					conexao.close();
				}
			} 
		}
	} 
	
	public List<Usuario> listarTodos() throws SQLException {
		List<Usuario> lista = new ArrayList<>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/dbServlet", 
					"postgres", 
					"guigui2006@"
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
					if (m.getId() == rs.getInt("municipio_id")) {
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
			e.printStackTrace();
		} finally {
			if (pStmt != null) {
				if (!pStmt.isClosed()) {
					pStmt.close();
				}
			} if (conexao != null) {
				if (!conexao.isClosed()) {
					conexao.close();
				}
			} 
		}
		return lista;
	} 
}