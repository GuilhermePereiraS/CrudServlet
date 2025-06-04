package cba.ifmt.ferramentas;

import java.sql.SQLException;

import cba.ifmt.DAO.MunicipioDao;

public class testa {

	public static void main(String[] args) throws SQLException {
		MunicipioDao dao = new MunicipioDao();
		dao.listaMunicipio().forEach(element -> {
			System.out.println(element.getNome());
		});
		

	}

}
