<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<style>
	input::placeholder {
		color:#d0dbd7;
	}
</style>
<title>Insert title here</title>
</head>
<body>
  <form action="deleta" method="post">
	  <h2>Insira o nome do usuario para deletar:</h2>
	  <input type="text" id="nome" name="nome" placeholder="digite aqui" required>
	  <input type="submit" value="Deletar">
	</form>
</body>
</html>