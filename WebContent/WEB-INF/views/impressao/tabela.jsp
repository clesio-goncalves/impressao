<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- percorre impress�es montando as linhas da tabela -->
<table class="table table-striped table-hover display" id="table_id">
	<thead>
		<tr>
			<th>Data</th>
			<th>Usu�rio</th>
			<th>P�ginas</th>
			<th>C�pias</th>
			<th>Impressora</th>
			<th>Documento</th>
			<th>Esta��o</th>
			<th>Duplex</th>
			<th>Escala Cinza</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="impressao" items="${impressoes}">
			<tr>
				<td><fmt:formatDate value="${impressao.data.time}"
						pattern="dd/MM/yyyy HH:mm:ss" /></td>
				<td>${impressao.usuarioPC.nome}</td>
				<td>${impressao.qnt_paginas}</td>
				<td>${impressao.qnt_copias}</td>
				<td>${impressao.impressora.nome}</td>
				<td>${impressao.documento}</td>
				<td>${impressao.estacao.nome}</td>

				<!-- DUPLEX -->
				<c:if test="${impressao.duplex eq true}">
					<td>Sim</td>
				</c:if>
				<c:if test="${impressao.duplex eq false}">
					<td>N�o</td>
				</c:if>

				<!-- ESCALA CINZA -->
				<c:if test="${impressao.escala_cinza eq true}">
					<td>Sim</td>
				</c:if>
				<c:if test="${impressao.escala_cinza eq false}">
					<td>N�o</td>
				</c:if>
			</tr>
		</c:forEach>
	</tbody>
</table>