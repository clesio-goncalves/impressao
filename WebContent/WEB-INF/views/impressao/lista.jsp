<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Relatório de Impressão</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.css">

<c:import url="../componentes/cabecalho.jsp" />

<div class="container">
	<div class="row">
		<div class="panel panel-success">
			<div class="panel-heading" data-toggle="collapse"
				data-target="#accordion">Filtros</div>

			<div class="panel-body collapse in" id="accordion">
				<fieldset class="col-md-6">
					<div class="row">
						<!-- DATA INICIAL -->
						<div class="form-group col-sm-6">
							<label for="data_inicial">Data inicial</label> <input type="text"
								class="form-control data" name="data_inicial" id="data_inicial"
								data-mask="99/99/9999">
						</div>

						<!-- DATA FINAL -->
						<div class="form-group col-sm-6">
							<label for="data_final">Data final (Não inclusa na
								listagem)</label> <input type="text" class="form-control data"
								name="data_final" id="data_final" data-mask="99/99/9999">
						</div>
					</div>

					<div class="row">

						<!-- MÍNIMO DE IMPRESSÕES -->
						<div class="form-group col-sm-4">
							<label for="qnt_impressoes">Mínimo de Impressões</label> <input
								type="number" class="form-control" name="qnt_impressoes"
								id="qnt_impressoes" onkeypress='return SomenteNumero(event)'>
						</div>

						<!-- USUÁRIO -->
						<div class="form-group col-sm-8">
							<label for="usuario">Usuário</label> <input type="text"
								class="form-control" name="usuario" id="usuario">
						</div>
					</div>

				</fieldset>

				<fieldset class="col-md-6">

					<div class="row">

						<!-- IMPRESSORA -->
						<div class="form-group col-sm-6">
							<label for="impressora">Impressora</label> <select
								class="form-control" name="impressora" id="impressora">
								<!-- percorre impressoras montando as linhas da tabela -->
								<option value="">Qualquer</option>
								<c:forEach var="impressora" items="${impressoras}">
									<option value="${impressora.id}">${impressora.nome}</option>
								</c:forEach>
							</select>
						</div>

						<!-- ESTAÇÃO -->
						<div class="form-group col-sm-6">
							<label for="estacao">Estação</label> <input type="text"
								class="form-control" name="estacao" id="estacao">
						</div>
					</div>

					<div class="row">
						<div class="form-group col-sm-6">
							<br />
							<div class="well">
								<b>Total de Impressões: ${total_impressao}</b>
							</div>
						</div>

						<div class="form-group col-sm-6" align="right">
							<br />
							<button type="reset" class="btn btn-default" id="limpar">
								<span class="glyphicon glyphicon-trash"></span> Limpar
							</button>
							<button type="button" class="btn btn-success" id="filtro">
								<span class="glyphicon glyphicon-filter"></span> Filtrar
							</button>
						</div>

					</div>
				</fieldset>
			</div>
		</div>
	</div>

	<!-- PAINEL -->
	<div class="row">
		<div class="panel panel-default">

			<div class="panel-heading">Relatório de Impressão</div>

			<!-- Table -->
			<div class="panel-body">
				<div class="table-responsive" id="tabela">
					<jsp:include page="tabela.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/inputmask-plugin.js"></script>
<script type="text/javascript" src="resources/js/SomenteNumero.js"></script>
<script type="text/javascript" src="resources/js/data_table.js"></script>
<script type="text/javascript" src="resources/js/filtro.js"></script>

<c:import url="../componentes/rodape.jsp" />