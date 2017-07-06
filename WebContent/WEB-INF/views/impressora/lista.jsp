<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"%>
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
							<th>Total Impress�es</th>
							<th>A��es</th>
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
									<td>N�o informado</td>
								</c:if>
								<c:if test="${impressora.setor != null}">
									<td>${impressora.setor.nome}</td>
								</c:if>

								<td>${impressora.total_impressao}</td>

								<!-- A��ES -->
								<td>
									<!-- Vincular --> <a href="editaImpressora?id=${impressora.id}"
									class="btn btn-info btn-xs"><span
										class="glyphicon glyphicon-link"></span> Vincular </a> <!-- Relat�rio -->
									<a href="listaImpressoes?id=${impressora.id}"
									class="btn btn-success btn-xs"><span
										class="glyphicon glyphicon-print"></span> Relat�rio</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>
</div>

<script src="resources/js/jquery.dataTables.js"></script>

<script>
	$(document).ready(function() {
		$('#table_id').DataTable({
			"language" : {
				"url" : "resources/idioma/Portuguese-Brasil.json"
			}
		});
	});
</script>

<c:import url="../componentes/rodape.jsp" />