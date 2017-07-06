<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Inicio</title>
<c:import url="componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Bem-vindo ${usuarioLogado.usuario} ao sistema Print CAPAU</h1>
		<p>
			Este � um prot�tipo de um sistema que emite relat�rios de impress�o.
		</p>
	</div>
</div>

<c:import url="componentes/rodape.jsp" />