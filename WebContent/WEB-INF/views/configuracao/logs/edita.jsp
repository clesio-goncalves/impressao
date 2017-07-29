<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
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

	<form action="alteraDiretorioLogs" method="POST">
		<!-- ID -->
		<input type="hidden" name="id" value="${configuracao.id}">

		<!-- Diretório dos Logs -->
		<div class="form-group">
			<label for="diretorio">Diretório dos Logs*</label> <input type="text"
				class="form-control" name="diretorio" MAXLENGTH="255" autofocus
				required value="${configuracao.diretorio}">
		</div>

		<!-- OBTIGATÓRIO -->
		<label for="obrigatorio">(*) Campo obrigatório</label>
		<div>
			<button type="button" class="btn btn-primary btn-lg"
				data-toggle="modal" data-target="#myModal">
				<span class="glyphicon glyphicon-refresh"></span> Atualizar
			</button>
		</div>

		<!-- Modal -->

		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Atualização</h4>
					</div>
					<div class="modal-body">
						<p>Deseja atualizar o diretório de logs?</p>
						<div align="right">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Não</button>
							<button type="submit" class="btn btn-success">Sim</button>
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>

</div>

<c:import url="../../componentes/rodape.jsp" />