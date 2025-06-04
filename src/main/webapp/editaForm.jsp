<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuário</title>
<style>
	form { padding: 10px;
	}
	
	form > input {
		margin: 5px;
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
		    <input id="nomeEncontrado" name="nomeEncontrado" type="text" value="${usuario.nome}" readonly><button id="botaoNome" type=button onclick="removerTagReadonly('nomeEncontrado')">editar</button><br>
		    <input id="email" type="text" name="email" value="${usuario.email}" readonly><button id="botaoEmail" type=button onclick="removerTagReadonly('email')">editar</button><br>
		    <input id="cpf" type="text" name="cpf" value="${usuario.cpf}" readonly>	<button id="botaoCpf" type=button onclick="removerTagReadonly('cpf')">editar</button><br>
		    <input id="municipio" type="text" name="municipio" value="${usuario.municipio.nome}" readonly><button id="botaoMunicipio" type=button onclick="removerTagReadonly('municipio')">editar</button><br>
		    <input id="id" type="text" name="id" value="${usuario.id}" readonly><br>
		   	<input type="submit" value="Concluir">
	  </form>
	  </div>
	</c:if>

<script type="text/javascript">
function removerTagReadonly(idInput) {
	input = document.getElementById(idInput);
	if (!input.hasAttribute("readonly")) {
		input.setAttribute("readonly",true)
	} else {
	input.removeAttribute("readonly");
		
	}
	
	
}


</script>
</body>
</html>