<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Vincular Impressora</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Vincular Impressora</h1>
		<p>Selecione o setor para vincular a impressora.</p>
	</div>
</div>
<div class="container">
	<form action="vinculaImpressora" method="POST">

		<!-- ID -->
		<input type="hidden" name="id" value="${impressora.id}" />

		<!-- IMPRESSORA -->
		<div class="form-group">
			<label for="nome">Impressora</label> <input type="text"
				class="form-control" name="nome" MAXLENGTH="100"
				value="${impressora.nome}" readonly>
		</div>

		<!-- SETOR -->
		<div class="form-group">
			<label for="setor">Setor*</label> <select class="form-control"
				name="setor.id" required>
				<!-- percorre setores montando as linhas da tabela -->
				<c:forEach var="st" items="${setores}">
					<option value="${st.id}"
						${st.id == impressora.setor.id ? 'selected' : ''}>${st.nome}</option>
				</c:forEach>
			</select>
		</div>

		<!-- OBTIGATÓRIO -->
		<label for="obrigatorio">(*) Campo obrigatório</label>
		<div>
			<a href="listaImpressoras" class="btn btn-default btn-lg"> <span
				class="glyphicon glyphicon-remove"></span> Cancelar
			</a>
			<button type="submit" class="btn btn-primary btn-lg">
				<span class="glyphicon glyphicon-refresh"></span> Atualizar
			</button>
		</div>
	</form>
</div>

<c:import url="../componentes/rodape.jsp" />