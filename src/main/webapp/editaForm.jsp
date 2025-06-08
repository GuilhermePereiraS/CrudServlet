<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuário</title>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
	form { padding: 10px;
	}
	
	form > input {
		margin: 5px;
	}
	
	input::placeholder {
		color:#d0dbd7;
	}
	
</style>
</head>
<body>
  <form action="procura" method="post">
	  <h2>Procure um usuário para editar</h2>
	  <input type="text" id="nome" name="nome" placeholder="digite aqui" required>
	  <input type="submit" value="Procurar">
	</form>
	
	<c:if test="${mostrarDiv}"> 
	  <div>
	  <form action="atualiza" method="post">
		    <input id="nomeEncontrado" name="nomeEncontrado" type="text" value="${usuario.nome}"><br>
		    <input id="email" type="text" name="email" value="${usuario.email}" ><br>
		    <input id="cpf" type="text" name="cpf" value="${usuario.cpf}" >	<br>
		    <input id="municipio" type="text" name="municipio" value="${usuario.municipio.nome}" ><br>
		    <input id="id" type="text" name="id" value="${usuario.id}" readonly><br>
		   	<input type="submit" value="Concluir">
	  </form>
	  </div>
	</c:if>

</body>
</html>