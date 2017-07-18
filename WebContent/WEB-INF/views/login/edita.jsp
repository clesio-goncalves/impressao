<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Editar Usuário</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Editar Usuário</h1>
		<p>Preencha o formulário abaixo para realizar a alteração.</p>
	</div>
</div>
<div class="container">
	<form action="alteraUsuario" method="POST">

		<!-- ID -->
		<input type="hidden" name="id" value="${login.id}" />

		<!-- USUARIO -->
		<div class="form-group">
			<label for="usuario">Usuário*</label> <input type="text"
				class="form-control" name="usuario" autofocus MAXLENGTH="20"
				required value="${login.usuario}">
		</div>

		<!-- SENHA -->
		<div class="form-group">
			<label for="senha">Senha*</label> <input type="password"
				class="form-control" name="senha" id="senha" MAXLENGTH="20" required
				value="${login.senha}">
		</div>

		<!-- REPETIR SENHA -->
		<div class="form-group">
			<label for="repetir_senha">Repetir Senha*</label> <input
				type="password" class="form-control" name="repetir_senha"
				id="repetir_senha" MAXLENGTH="20" required value="${login.senha}">
		</div>

		<!-- SETOR -->
		<div class="form-group">
			<label for="setor">Setor*</label> <select class="form-control"
				name="setor.id" required>
				<!-- percorre setores montando as linhas da tabela -->
				<c:forEach var="st" items="${setores}">
					<option value="${st.id}"
						${st.id == login.setor.id ? 'selected' : ''}>${st.nome}</option>
				</c:forEach>
			</select>
		</div>

		<!-- PERFIL -->
		<div class="form-group">
			<label for="perfil">Perfil*</label>
			<c:forEach var="perfil" items="${perfis}">
				<div class="radio">
					<label> <input type="radio" name="perfil" value="${perfil}"
						${perfil eq login.perfil ? 'checked' : ''}>${perfil}
					</label>
				</div>
			</c:forEach>
		</div>

		<!-- ATIVO -->
		<div class="form-group">
			<div class="checkbox">
				<label> <input type="checkbox" name="ativo"
					${login.ativo ? 'checked' : ''}> Ativo
				</label>
			</div>
		</div>

		<!-- OBTIGATÓRIO -->
		<label for="obrigatorio">(*) Campos obrigatórios</label>
		<div>
			<a href="listaUsuarios" class="btn btn-default btn-lg"> <span
				class="glyphicon glyphicon-remove"></span> Cancelar
			</a>
			<button type="submit" class="btn btn-primary btn-lg">
				<span class="glyphicon glyphicon-refresh"></span> Atualizar
			</button>
		</div>
	</form>
</div>

<script src="resources/js/confirma_senha.js"></script>

<c:import url="../componentes/rodape.jsp" />