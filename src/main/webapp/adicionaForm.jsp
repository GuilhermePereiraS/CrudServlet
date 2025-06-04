<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
label {
	margin: 30px;
}
</style>
</head>
<body>
	<form action="adiciona" method="post">
	  <label for="nome">Nome:</label>
	  <input type="text" id="nome" name="nome" required><br>
	
	  <label for="email">E-mail:</label>
	  <input type="email" id="email" name="email" required><br>
	
	  <label for="cpf">Cpf:</label>
	  <input type="text" id="cpf" name="cpf" required><br>
	
	  <select>
	  	<c:forEach var="municipios" items="${municipios}">
	  		<option value="${municipios.nome}">${municipios.nome}</option>
	  	</c:forEach>
	  </select>
	
	  <input type="submit" value="Adicionar">
	</form>
</body>
</html>