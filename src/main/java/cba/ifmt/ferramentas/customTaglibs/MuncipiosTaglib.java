package cba.ifmt.ferramentas.customTaglibs;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import cba.ifmt.DAO.MunicipioDao;
import cba.ifmt.entidades.Municipio;

public class MuncipiosTaglib extends SimpleTagSupport {
	public void doTag() throws IOException {
		MunicipioDao dao = new MunicipioDao();
		
		JspWriter out = getJspContext().getOut(); 
		try {
			for (Municipio m : dao.listaMunicipio()) {
				out.println(m.getNome());
			}
		} catch (SQLException e) {
			System.err.println("Deu ruim com a conexao com o db sql: " + e.getMessage());
		}
		
	}
}
