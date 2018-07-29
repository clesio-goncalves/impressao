<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/login.css" />

</head>
<body>
	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Login em Print CAPAU</h3>
				</div>
				<div class="panel-body">
					<form action="login" method="POST">
						<c:if test="${param.error != null}">
							<p>Usuário e/ou senha inválido.</p>
						</c:if>

						<c:if test="${param.logout != null}">
							<p>Logout realizado com sucesso.</p>
						</c:if>

						<div class="form-group">
							<label for="username">Usuário</label> <input class="form-control"
								id="username" name="username" MAXLENGTH="50" type="text"
								required autofocus>
						</div>
						<div class="form-group">
							<label for="senha">Senha</label> <input class="form-control"
								id="password" name="password" MAXLENGTH="50" type="password"
								required>
						</div>
						<security:csrfInput />
						<button type="submit" class="btn btn-lg btn-success btn-block">Entrar</button>
					</form>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.js"></script>

</body>
</html>
