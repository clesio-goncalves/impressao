<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Listar Usuários</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.css">

<c:import url="../componentes/cabecalho.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Listagem de impressões oriundas de Estações com IPv6 Ativado</div>

		<!-- Table -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-hover display" id="table_id">
					<thead>
						<tr>
							<th>Data</th>
							<th>Usuário</th>
							<th>Impressora</th>
							<th>Documento</th>
							<th>Estação</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="impressao" items="${impressoes}">
							<tr>
								<td><fmt:formatDate value="${impressao.data.time}"
										pattern="dd-MM-yyyy HH:mm:ss" /></td>
								<td>${impressao.usuarioPC.nome}</td>
								<td>${impressao.impressora.nome}</td>
								<td>${impressao.documento}</td>
								<td>${impressao.estacao.nome}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/data_table.js"></script>

<c:import url="../componentes/rodape.jsp" />