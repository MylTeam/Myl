<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<jsp:text>
	<![CDATA[
			<script src="${pageContext.request.contextPath}/pages/edicion/js/index.js" type="text/javascript"></script>
		 ]]>
</jsp:text>
</head>
<body>

	<center>
		<table id="tblEdicion" style="width: 100%;">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Siglas</th>
					<th>No. de cartas</th>
					<th>Estatus</th>
					<th style="text-align: center;">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="ediciones">
					<tr>
						<td>${nombre}</td>
						<td>${siglas}</td>
						<td><s:property value="cartas.size()" /></td>
						<td></td>
						<td style="text-align: center; width: 18%"> <a
							href="${pageContext.request.contextPath}/deck/${deckId}/deleteConfirm">
								<img height="40" width="40"
								src="${pageContext.request.contextPath}/images/buttons/elim.png"
								title="Eliminar deck" />
						</a> <a
							href="${pageContext.request.contextPath}/deck/${deckId}/deleteConfirm">
								<img height="40" width="40"
								src="${pageContext.request.contextPath}/images/buttons/deck.png"
								title="Agregar spoiler" />
						</a> <a
							href="${pageContext.request.contextPath}/deck/${deckId}/deleteConfirm">
								<img height="40" width="40"
								src="${pageContext.request.contextPath}/images/buttons/blank-card.png"
								title="Gestionar Cartas" />
						</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</center>
	<div id="btnNew">		
		<button type="button" onclick="addEdition()">Agregar Edición</button>
	</div>

</body>
	</html>
</jsp:root>
