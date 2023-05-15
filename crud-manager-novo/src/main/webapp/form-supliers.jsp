<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="base-head.jsp"%>
</head>
<body>
	<%@include file="nav-menu.jsp"%>

	<div id="container" class="container-fluid">

		<h3 class="page-header">Adicionar Fornecedor</h3>

		<form action="${pageContext.request.contextPath}/company/${action}"
			method="POST">
			<div class="row">
				<div class="form-group col-md-4">
					<label for="name">Nome</label> <input type="text"
						class="form-control" id="name" name="name" autofocus="autofocus"
						placeholder="Nome Fornecedor" required
						oninvalid="this.setCustomValidity('Por favor, informe o nome do fornecedor.')"
						oninput="setCustomValidity('')">
				</div>

				<div class="form-group col-md-4">
					<label for="phone">Telefone</label> <input type="text"
						class="form-control" id="phone" name="phone" autofocus="autofocus"
						placeholder="Telefone Fornecedor" required
						oninvalid="this.setCustomValidity('Por favor, informe telefone.')"
						oninput="setCustomValidity('')">
				</div>
				
				
				<div class="form-group col-md-4">
					<label for="phone">Email</label> <input type="text"
						class="form-control" id="email" name="email" autofocus="autofocus"
						placeholder="Email Fornecedor" required
						oninvalid="this.setCustomValidity('Por favor, informe o email.')"
						oninput="setCustomValidity('')">
				</div>
				

			</div>

			<div class="row">
				<div class="form-group col-md-4">
					<label for="company">Fornecedor vinculado a empresa:</label>
					 <select id="company"
						class="form-control selectpicker" name="company" required
						oninvalid="this.setCustomValidity('Por favor, informe a empresa.')"
						oninput="setCustomValidity('')">
						<option value="">Selecione
							uma empresa
						</option>
						<c:forEach var="company" items="${company}">
							<option value="${company.getId()}">
								${company.getName()}
							</option>
						</c:forEach>
					</select>
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/supliers" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">Cadastrar fornecedor</button>
					</div>
				</div>

			</div>

		</form>
	</div>

</body>
</html>