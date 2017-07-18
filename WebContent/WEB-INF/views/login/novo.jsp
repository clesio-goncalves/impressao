<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastrar Usuário</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Cadastrar Usuário</h1>
		<p>Preencha o formulário abaixo para realizar o cadastro do
			usuário para realiza login no sistema.</p>
	</div>
</div>
<div class="container">
	<form action="adicionaUsuario" method="POST">

		<!-- USUARIO -->
		<div class="form-group">
			<label for="usuario">Usuário*</label> <input type="text"
				class="form-control" name="usuario" autofocus MAXLENGTH="20"
				required>
		</div>

		<!-- SENHA -->
		<div class="form-group">
			<label for="senha">Senha*</label> <input type="password"
				class="form-control" name="senha" id="senha" MAXLENGTH="20" required>
		</div>

		<!-- REPETIR SENHA -->
		<div class="form-group">
			<label for="repetir_senha">Repetir Senha*</label> <input
				type="password" class="form-control" name="repetir_senha"
				id="repetir_senha" MAXLENGTH="20" required>
		</div>

		<!-- SETOR -->
		<div class="form-group">
			<label for="setor">Setor*</label> <select class="form-control"
				name="setor.id" required>
				<!-- percorre setores montando as linhas da tabela -->
				<c:forEach var="setor" items="${setores}">
					<option value="${setor.id}">${setor.nome}</option>
				</c:forEach>
			</select>
		</div>

		<!-- PERFIL -->
		<div class="form-group">
			<label for="perfil">Perfil*</label>
			<c:forEach var="perfil" items="${perfis}">
				<div class="radio">
					<label> <input type="radio" name="perfil" value="${perfil}"
						checked="checked">${perfil}
					</label>
				</div>
			</c:forEach>
		</div>

		<!-- ATIVO -->
		<div class="form-group">
			<div class="checkbox">
				<label> <input type="checkbox" name="ativo"
					checked="checked"> Ativo
				</label>
			</div>
		</div>

		<!-- OBTIGATÓRIO -->
		<label for="obrigatorio">(*) Campos obrigatórios</label>
		<div>
			<button type="reset" class="btn btn-default btn-lg">
				<span class="glyphicon glyphicon-trash"></span> Limpar
			</button>
			<button type="submit" class="btn btn-primary btn-lg">
				<span class="glyphicon glyphicon-saved"></span> Salvar
			</button>
		</div>
	</form>
</div>

<script src="resources/js/confirma_senha.js"></script>

<c:import url="../componentes/rodape.jsp" />