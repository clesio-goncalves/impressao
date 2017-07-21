<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Diretório dos Logs</title>

<c:import url="../../componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Diretório dos Logs</h1>
		<p>
			Selecione abaixo a pasta dos logs do Paper Cut. <br /> <br /> OBS:
			Caso a pasta esteja localizado em outro servidor é necessário
			compartilhar essa pasta para que a aplicação Print CAPAU consiga
			acessar os logs de impressão.
		</p>
	</div>
</div>

<div class="container">
	<form action="adicionaDiretorioLogs" method="POST">
		<!-- Diretório dos Logs -->
		<div class="form-group">
			<label for="diretorio">Diretório dos Logs*</label> <input type="text"
				class="form-control" name="diretorio" MAXLENGTH="255" autofocus
				required>
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

<c:import url="../../componentes/rodape.jsp" />