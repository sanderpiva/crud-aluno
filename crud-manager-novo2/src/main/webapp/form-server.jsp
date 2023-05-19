<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<%@include file="base-head.jsp"%>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
			
		<div id="container" class="container-fluid">
			<h3 class="page-header">Adicionar Servidor</h3>

			<form action="${pageContext.request.contextPath}/server/${action}" method="POST">
				<input type="hidden" name="serverId">
				<div class="row">
					<div class="form-group col-md-4">
					<label for="name">Nome</label>
						<input type="text" class="form-control" id="name" name="name" 
							   autofocus="autofocus" placeholder="Nome do servidor" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o nome do servidor.')"
							   oninput="setCustomValidity('')"
							   value="${server.getName()}">
					</div>
					
					<div class="form-group col-md-4">
					<label for="phone">Telefone</label>
						<input type="text" class="form-control" id="phone" name="phone" 
							   autofocus="autofocus" placeholder="Telefone do servidor (xx)xxxx-xxxx" 
							   required oninvalid="this.setCustomValidity('Por favor, informe telefone do servidor.')"
							   oninput="setCustomValidity('')"
							   value="${server.getTelephone()}">
					</div>
					
					<div class="form-group col-md-4">
					<label for="email">E-mail</label>
						<input type="email" class="form-control" id="email" name="email" 
							   autofocus="autofocus" placeholder="E-mail do servidor" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o email do servidor.')"
							   oninput="setCustomValidity('')"
							   value="${server.getEmail()}">
					</div>
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/servers" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${not empty server ? "Alterar Servidor" : "Cadastrar Servidor"}</button>
					</div>
				</div>
			</form>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>
