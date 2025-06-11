	package cba.ifmt.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cba.ifmt.DAO.MunicipioDao;
import cba.ifmt.DAO.UsuarioDao;
import cba.ifmt.entidades.Municipio;
import cba.ifmt.entidades.Usuario;

/**
 * Servlet implementation class adicionaServlet
 */

public class AdicionaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public AdicionaServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //IMPORTANE DEMAIS, SENÃO O REQUEST.GETPARAMETER DEVOLVE EM ISO-8859-1 
		Municipio municipioDesejado = null;
		MunicipioDao mDao = new MunicipioDao();
		UsuarioDao uDao = new UsuarioDao();
		try {
			Usuario usuario = new Usuario();
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setCpf(request.getParameter("cpf"));
			
			
			for (Municipio m : mDao.listaMunicipio()) {
				if (m.getNome().equals(request.getParameter("municipio"))) {
					municipioDesejado = m;
					break;
				}
			}
			
			if (municipioDesejado == null) {
			    throw new ServletException("Município informado não foi encontrado: " + municipioDesejado);
			}
			
			usuario.setMunicipio(municipioDesejado);
			uDao.adicionarUsuarioNoDb(usuario);
			
			
			response.sendRedirect("consulta");
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
