<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Listar Setores</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.css">

<c:import url="../componentes/cabecalho.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Listagem de Setores</div>

		<!-- Table -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-hover display" id="table_id">
					<thead>
						<tr>
							<th>ID</th>
							<th>Nome</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<!-- percorre setores montando as linhas da tabela -->
						<c:forEach var="setor" items="${setores}">
							<tr>
								<td>${setor.id}</td>
								<td>${setor.nome}</td>

								<!-- AÇÕES -->
								<td>
									<!-- Exibir --> <a href="exibeSetor?id=${setor.id}"
									class="btn btn-success btn-xs"><span
										class="glyphicon glyphicon-zoom-in"></span> Exibir</a> <!-- Editar -->
									<a href="editaSetor?id=${setor.id}" class="btn btn-info btn-xs"><span
										class="glyphicon glyphicon-edit"></span> Editar </a> <!-- Botão exluir -->
									<button class="btn btn-danger btn-xs" data-toggle="modal"
										data-target="#${setor.id}">
										<span class="glyphicon glyphicon-trash"></span> Excluir
									</button> <!-- Modal -->
									<div class="modal fade" id="${setor.id}" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only">Fechar</span>
													</button>
													<h4 class="modal-title" id="myModalLabel">Exclusão do
														setor</h4>
												</div>
												<div class="modal-body">Deseja realmente excluir o
													setor (${setor.id}) -> ${setor.nome}?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">
														<span class="glyphicon glyphicon-log-out"></span> Fechar
													</button>
													<a href="removeSetor?id=${setor.id}" class="btn btn-danger"><span
														class="glyphicon glyphicon-trash"></span> Excluir</a>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>
	<a href="novoSetor" class="btn btn-primary btn-lg"><span
		class="glyphicon glyphicon-plus"></span> Cadastrar</a>
</div>

<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/data_table.js"></script>

<c:import url="../componentes/rodape.jsp" />