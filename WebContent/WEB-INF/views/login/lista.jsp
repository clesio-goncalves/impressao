<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Listar Usuários</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.css">

<c:import url="../componentes/cabecalho.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Listagem de Usuários</div>

		<!-- Table -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-hover display" id="table_id">
					<thead>
						<tr>
							<th>ID</th>
							<th>Usuário</th>
							<th>Ativo</th>
							<th>Perfil</th>
							<th>Setor</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<!-- percorre logins montando as linhas da tabela -->
						<c:forEach var="login" items="${logins}">
							<tr>
								<td>${login.id}</td>
								<td>${login.usuario}</td>

								<!-- Ativo -->
								<c:if test="${login.ativo eq true}">
									<td>Sim</td>
								</c:if>
								<c:if test="${login.ativo eq false}">
									<td>Não</td>
								</c:if>

								<td>${login.perfil}</td>
								<td>${login.setor.nome}</td>

								<!-- AÇÕES -->
								<td>
									<!-- Exibir --> <a href="exibeUsuario?id=${login.id}"
									class="btn btn-success btn-xs"><span
										class="glyphicon glyphicon-zoom-in"></span> Exibir</a> <!-- Editar -->
									<a href="editaUsuario?id=${login.id}"
									class="btn btn-info btn-xs"><span
										class="glyphicon glyphicon-edit"></span> Editar </a> <!-- Botão exluir -->
									<button class="btn btn-danger btn-xs" data-toggle="modal"
										data-target="#${login.id}">
										<span class="glyphicon glyphicon-trash"></span> Excluir
									</button> <!-- Modal -->
									<div class="modal fade" id="${login.id}" tabindex="-1"
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
														usuário</h4>
												</div>
												<div class="modal-body">Deseja realmente excluir o
													usuário (${login.id}) -> ${login.usuario}?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">
														<span class="glyphicon glyphicon-log-out"></span> Fechar
													</button>
													<a href="removeUsuario?id=${login.id}"
														class="btn btn-danger"><span
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
	<a href="novoUsuario" class="btn btn-primary btn-lg"><span
		class="glyphicon glyphicon-plus"></span> Cadastrar</a>
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