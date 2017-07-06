<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastrar Setor</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Cadastrar Setor</h1>
		<p>Preencha o formulário abaixo para realizar o cadastro do setor.</p>
	</div>
</div>
<div class="container">
	<form action="adicionaSetor" method="POST">

		<!-- NOME -->
		<div class="form-group">
			<label for="nome">Nome*</label> <input type="text"
				class="form-control" name="nome" autofocus MAXLENGTH="20" required>
		</div>

		<!-- OBTIGATÓRIO -->
		<label for="obrigatorio">(*) Campo obrigatório</label>
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

<c:import url="../componentes/rodape.jsp" />