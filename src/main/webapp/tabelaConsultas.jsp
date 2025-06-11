<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Consulta de usuários</title>
<link rel="stylesheet" type="text/css" href="style.css">
<style>

table {
	border-collapse: collapse;
	background-color: #7da195;
	border: solid 2px #b8dbd0;
}

table::selection {
	background-color: #769188
}
tr,td,th {
	border: 2px, solid;
	padding: 5px;
	border-radius: 5px;
}

</style>
</head>
<body>
<table>
	<thead>
	    <tr>
	      <th>Nome</th>
	      <th>Email</th>
	      <th>CPF</th>
	      <th>Municipio</th>
	    </tr>
	</thead>
    
	<c:forEach var="usuario" items="${usuarios}">
	    <tr>
	      <td>${usuario.nome}</td>
	      <td>${usuario.email}</td>
	      <td>${usuario.cpf}</td>
	      <td>${usuario.municipio.nome}</td>
    	</tr>
	</c:forEach>
  </tbody>
  
</table>
</body>
</html>