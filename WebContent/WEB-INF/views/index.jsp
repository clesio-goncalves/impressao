<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Inicio</title>
<c:import url="componentes/cabecalho.jsp" />

<div class="jumbotron">
	<div class="container">
		<h1>Bem-vindo, ${usuarioLogado.nome}</h1>
		<p>Este é o protótipo do sistema Print Capau que emite relatórios
			de impressão.</p>
		<p>OBS.: Para o sistema ter acesso ao relatório de impressão é
			necessário informar o diretório de Logs do Paper Cut no Menu de
			Configuração.</p>
	</div>
</div>

<c:import url="componentes/rodape.jsp" />