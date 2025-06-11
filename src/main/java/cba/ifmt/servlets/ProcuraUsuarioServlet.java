package cba.ifmt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cba.ifmt.DAO.UsuarioDao;
import cba.ifmt.entidades.Usuario;


public class ProcuraUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcuraUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("editaForm.jsp").forward(	request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nomeProcurado = request.getParameter("nome");
		Usuario usuario = null;
		try {
			
			UsuarioDao uDao = new UsuarioDao();
			usuario = uDao.consultarUsuario(nomeProcurado);
			
		} catch (Exception e) {
			
		}
		
		request.setAttribute("usuario", usuario);
		request.setAttribute("mostrarDiv", true);
		
		request.getRequestDispatcher("editaForm.jsp").forward(request, response);
		
	}

}
