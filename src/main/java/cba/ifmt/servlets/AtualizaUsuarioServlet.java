package cba.ifmt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cba.ifmt.DAO.MunicipioDao;
import cba.ifmt.DAO.UsuarioDao;
import cba.ifmt.entidades.Municipio;
import cba.ifmt.entidades.Usuario;

/**
 * Servlet implementation class AtualizaUsuarioServlet
 */
public class AtualizaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizaUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		Municipio municipioDesejado = null;
		MunicipioDao mDao = new MunicipioDao();
		UsuarioDao uDao = new UsuarioDao();
		try {
			
			Usuario usuario = new Usuario();
			usuario.setNome(request.getParameter("nomeEncontrado"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setCpf(request.getParameter("cpf"));
			usuario.setId(Integer.parseInt(request.getParameter("id")));
			
			
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
			uDao.editarUsuario(usuario);
			
		} catch (Exception e) {
			
		}
		request.getRequestDispatcher("tudoCerto.jsp").forward(request, response);
	}
	
	

}
