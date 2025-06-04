package cba.ifmt.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cba.ifmt.DAO.MunicipioDao;



public class EnviaMunicipiosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EnviaMunicipiosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MunicipioDao mDao = new MunicipioDao();
			request.setAttribute("municipios", mDao.listaMunicipio());
			request.getRequestDispatcher("adicionaForm.jsp").forward(request, response);
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		}
		
		
	}


}
