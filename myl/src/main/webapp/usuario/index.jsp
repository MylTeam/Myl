<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Perfil</title>
<jsp:text>
	<![CDATA[			 		
							
		 ]]>
</jsp:text>
</head>
<body>
<form style="width: 80%; border: 0px">
	<input type="hidden" name="hidden" id="hidden" value="${pageContext.request.contextPath}" />
	${usuario.login}
	
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
					<s:iterator value="lista">
						<tr>
							<td>${deckNombre}</td>
							<td width="90">
									<a href="${pageContext.request.contextPath}/deck/${deckId}/edit">
									<img height="40" width="40" src="${pageContext.request.contextPath}/images/buttons/botEditar2.png" title="Modificar deck"/></a>
							
									<a href="${pageContext.request.contextPath}/deck/${deckId}/deleteConfirm">
									<img height="40" width="40" src="${pageContext.request.contextPath}/images/buttons/eliminar.png" title="Eliminar deck"/></a>
							</td>
						</tr>
					</s:iterator>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/deck/new"><input type="button" value="Nuevo Deck"></input></a>
	</form>
</body>
	</html>
</jsp:root>
