package cba.ifmt.ferramentas.customTaglibs;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import cba.ifmt.DAO.MunicipioDao;

public class MuncipiosTaglib extends SimpleTagSupport {
	public void doTag() {
		MunicipioDao dao = new MunicipioDao();
		
		/*JspWriter out = getJspContext().getOut(); 
		for (Municipio m : dao.listaMunicipio()) {
			
		}
		*/
	}
}
