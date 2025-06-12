package cba.ifmt.ferramentas;

import java.sql.SQLException;

import cba.ifmt.DAO.UsuarioDao;
import cba.ifmt.entidades.Usuario;

public class testa {

	public static void main(String[] args) throws SQLException {
		
		UsuarioDao	uDao = new UsuarioDao();
		Usuario u = uDao.consultarUsuario("guilherminho");
		System.out.println(u.getNome());

	}

}
