<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link type="text/css"
	href="${pageContext.request.contextPath}/Estilos/default.css?123"
	rel="stylesheet" />
<link type="text/css"
	href="${pageContext.request.contextPath}/Estilos/smoothness/jquery-ui-1.8.22.custom.css"
	rel="stylesheet" />
<link type="text/css"
	href="${pageContext.request.contextPath}/scripts/jquery.contextMenu.css"
	rel="stylesheet" />
<jsp:text>
	<![CDATA[			 		
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/jquery-ui-1.8.22.custom.min.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/jquery.contextMenu.js" type="text/javascript"></script>				
		 ]]>
</jsp:text>

<title>Perfil</title>

</head>
<body>

	<input type="hidden" name="hidden" id="hidden"
		value="${pageContext.request.contextPath}" />

	${usuario.login}

	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
					<s:iterator value="decks">
						<tr>
							<td>${deckNombre}</td>
							<td width="90">
									<a href="${pageContext.request.contextPath}/deck/${idDeck}/edit?idUsuarioSel=${idSel}">
									<img height="40" width="40" src="${pageContext.request.contextPath}/images/buttons/botEditar2.png" title="Modificar deck"/></a>
							
									<a href="${pageContext.request.contextPath}/deck/${idDeck}/deleteConfirm?idUsuarioSel=${idSel}">
									<img height="40" width="40" src="${pageContext.request.contextPath}/images/buttons/eliminar.png" title="Eliminar deck"/></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>

	</table>
</body>
	</html>
</jsp:root>
