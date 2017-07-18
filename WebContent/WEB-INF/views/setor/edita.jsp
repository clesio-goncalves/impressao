<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Editar Setor</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Editar Setor</h1>
		<p>Preencha o formulário abaixo para realizar a alteração do
			setor.</p>
	</div>
</div>
<div class="container">
	<form action="alteraSetor" method="POST">

		<!-- ID -->
		<input type="hidden" name="id" value="${setor.id}">

		<!-- NOME -->
		<div class="form-group">
			<label for="nome">Nome*</label> <input type="text"
				class="form-control" name="nome" autofocus MAXLENGTH="20" required
				value="${setor.nome}">
		</div>

		<!-- OBTIGATÓRIO -->
		<label for="obrigatorio">(*) Campo obrigatório</label>
		<div>
			<a href="listaSetores" class="btn btn-default btn-lg"> <span
				class="glyphicon glyphicon-remove"></span> Cancelar
			</a>
			<button type="submit" class="btn btn-primary btn-lg">
				<span class="glyphicon glyphicon-refresh"></span> Atualizar
			</button>
		</div>
	</form>
</div>

<c:import url="../componentes/rodape.jsp" />