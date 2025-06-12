package cba.ifmt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cba.ifmt.DAO.UsuarioDao;
import cba.ifmt.entidades.Usuario;


public class DeletaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nomeProcurado = request.getParameter("nome");
		Usuario usuario = null;
		try {
			
			UsuarioDao uDao = new UsuarioDao();
			usuario = uDao.consultarUsuario(nomeProcurado);
			uDao.deletarUsuarioNoDb(usuario);
			
		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}
		
		response.sendRedirect("tudoCerto.jsp");
	}

}
