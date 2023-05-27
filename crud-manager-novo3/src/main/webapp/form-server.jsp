<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@include file="base-head.jsp"%>
</head>
<body>
	<%@include file="nav-menu.jsp"%>

	<div id="container" class="container-fluid">
		<h3 class="page-header">${not empty server ? 'Atualizar' : 'Adicionar'} Servidor</h3>

		<form action="${pageContext.request.contextPath}/server/${action}"
			method="POST">
			<input type="hidden" value="${server.getId()}" name="serverId">
			
			<div class="row">
				<div class="form-group col-md-3">
					<label for="name">Nome</label> <input type="text"
						class="form-control" id="name" name="name" autofocus="autofocus"
						placeholder="Nome do servidor" required
						oninvalid="this.setCustomValidity('Por favor, informe o nome do servidor.')"
						oninput="setCustomValidity('')" value="${server.getName()}">
				</div>
				
				<div class="form-group col-md-3">
					<label for="address">Endereco</label> <input type="text"
						class="form-control" id="address" name="address" autofocus="autofocus"
						placeholder="Endereço do servidor" required
						oninvalid="this.setCustomValidity('Por favor, informe endereço do servidor.')"
						oninput="setCustomValidity('')" value="${server.getAddress()}">
				</div>
				
				<div class="form-group col-md-3">
					<label for="phone">Telefone</label> <input type="text"
						class="form-control" id="phone" name="phone" autofocus="autofocus"
						placeholder="Telefone do servidor" required
						oninvalid="this.setCustomValidity('Por favor, informe telefone do servidor.')"
						oninput="setCustomValidity('')" value="${server.getTelephone()}">
				</div>
				

				<div class="form-group col-md-3">
					<label for="email">E-mail</label> <input type="email"
						class="form-control" id="email" name="email" autofocus="autofocus"
						placeholder="E-mail do servidor" required
						oninvalid="this.setCustomValidity('Por favor, informe o email do servidor.')"
						oninput="setCustomValidity('')" value="${server.getEmail()}">
				</div>
			</div>
			<div class="row">

				<div class="form-group col-md-4">
					<label for="company">Empresa cliente</label>
					<select id="company"
						class="form-control selectpicker" name="company" required
						oninvalid="this.setCustomValidity('Por favor, informe a empresa.')"
						oninput="setCustomValidity('')">
						<option value="" ${not empty server ? "" : 'selected'}>Selecione uma empresa 
						</option>
						<c:forEach var="comp" items="${companies}">
							<option value="${comp.getId()}" ${server.getCompany().getId() == comp.getId() ? 'selected' : ''}>
							${comp.getName()}
							</option>
						</c:forEach>
					</select>
				</div>

			</div>

			<hr />
			<div id="actions" class="row pull-right">
				<div class="col-md-12">
					<a href="${pageContext.request.contextPath}/servers"
						class="btn btn-default">Cancelar</a>
					<button type="submit" class="btn btn-primary">
					${not empty server ? 'Atualizar' : 'Cadastrar'} Servidor</button>
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
