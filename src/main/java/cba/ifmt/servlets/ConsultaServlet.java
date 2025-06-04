package cba.ifmt.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cba.ifmt.DAO.UsuarioDao;

public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doBoth(request,response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doBoth(request,response);
	}
	
	private void doBoth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UsuarioDao uDao = new UsuarioDao();
		try {
			
			request.setAttribute("usuarios",uDao.listarTodos());
			request.getRequestDispatcher("tabelaConsultas.jsp").forward(request, response);
		} catch (Exception e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
