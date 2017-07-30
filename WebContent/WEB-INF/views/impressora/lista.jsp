<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Vincular Impressora</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.css">

<c:import url="../componentes/cabecalho.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Lista Impressoras</div>

		<!-- Table -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-hover display" id="table_id">
					<thead>
						<tr>
							<th>ID</th>
							<th>Impressora</th>
							<th>Setor</th>
							<th>Total Impressões</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<!-- percorre impressoras montando as linhas da tabela -->
						<c:forEach var="impressora" items="${impressoras}">
							<tr>
								<td>${impressora.id}</td>
								<td>${impressora.nome}</td>

								<!-- Setor -->
								<c:if test="${impressora.setor == null}">
									<td>Não informado</td>
								</c:if>
								<c:if test="${impressora.setor != null}">
									<td>${impressora.setor.nome}</td>
								</c:if>

								<td>${impressora.total_impressao}</td>

								<!-- AÇÕES -->
								<td>
									<!-- Vincular --> <a href="editaImpressora?id=${impressora.id}"
									class="btn btn-info btn-xs"><span
										class="glyphicon glyphicon-link"></span> Vincular </a> <!-- Relatório -->
									<a href="listaImpressoes?id=${impressora.id}"
									class="btn btn-success btn-xs"><span
										class="glyphicon glyphicon-print"></span> Relatório</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>

		<div align="center">
			<!-- PDF -->
			<a href="relatorioImpressora" class="btn btn-primary"><span
				class="glyphicon"></span> PDF</a>
		</div>

	</div>
</div>

<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/data_table.js"></script>

<c:import url="../componentes/rodape.jsp" />