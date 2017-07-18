<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Exibe os dados do setor</title>
<c:import url="../componentes/cabecalho.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Exibe os dados do setor</div>
		<!-- Table -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-hover">
					<tr>
						<th width="300">ID</th>
						<td>${setor.id}</td>
					</tr>

					<tr>
						<th>Nome</th>
						<td>${setor.nome}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div align="center">
		<!-- Cadastrar -->
		<a href="novoSetor" class="btn btn-primary btn-lg"><span
			class="glyphicon glyphicon-plus"></span> Cadastrar</a>
		<!-- Editar -->
		<a href="editarSetor?id=${setor.id}" class="btn btn-info btn-lg"><span
			class="glyphicon glyphicon-edit"></span> Editar </a>
		<!-- Excluir -->
		<button class="btn btn-danger btn-lg" data-toggle="modal"
			data-target="#${setor.id}">
			<span class="glyphicon glyphicon-trash"></span> Excluir
		</button>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="${setor.id}" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Exclusão do setor</h4>
				</div>
				<div class="modal-body">Deseja realmente excluir o setor
					(${setor.id}) -> ${setor.nome}?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-log-out"></span> Fechar
					</button>
					<a href="removeSetor?id=${setor.id}" class="btn btn-danger"><span
						class="glyphicon glyphicon-trash"></span> Excluir</a>
				</div>
			</div>
		</div>
	</div>
	<ul class="pager">
		<li class="previous"><a href="listaSetores"><span
				class="glyphicon glyphicon-chevron-left"></span> Voltar</a></li>
	</ul>
</div>

<c:import url="../componentes/rodape.jsp" />