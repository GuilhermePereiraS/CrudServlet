<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form action="adiciona" method="get">
	  <label for="nome">Nome:</label>
	  <input type="text" id="nome" name="nome" required><br>
	
	  <label for="email">E-mail:</label>
	  <input type="email" id="email" name="email" required><br>
	
	  <label for="cpf">Cpf:</label>
	  <input type="text" id="cpf" name="cpf" required><br>
	
	  <select>
	  	<option value=""></option>
	  </select>
	
	  <input type="submit" value="Adicionar">
	</form>
</body>
</html>