<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Diretório dos Logs</title>

<c:import url="../componentes/cabecalho.jsp" />

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
<div class="container"></div>

<c:import url="../componentes/rodape.jsp" />