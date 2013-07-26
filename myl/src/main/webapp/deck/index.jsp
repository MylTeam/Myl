<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<!--Es llamado por struts (GestionarAreas 14)-->
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Gestionar Temas</title>
<jsp:text>
	<![CDATA[ 
					
		 ]]>
</jsp:text>
</head>
<body>
	<form style="width: 80%; border: 0px">
		<center>
			<h1>Gestionar Temas</h1>
			<s:actionmessage id="algo" theme="jquery" />
			
				<table id="tblTema">
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
								<td width="90"><a
									href="${pageContext.request.contextPath}/catalogo-tema/${idTema}/edit.action"><img
										height="40" width="40"
										src="${pageContext.request.contextPath}/images/buttons/botEditar2.png" /></a>
									<a
									href="${pageContext.request.contextPath}/catalogo-tema/${idTema}/deleteConfirm.action"><img
										height="40" width="40"
										src="${pageContext.request.contextPath}/images/buttons/eliminar.png" /></a>
								</td>
							</tr>
						</s:iterator>
					</tbody>

				</table>
			</center>
	</form>
	<center>
		<a href="${pageContext.request.contextPath}/catalogo-tema/new.action"><input
			type="submit" value="Agregar Tema"></input></a>
	</center>
</body>
	</html>
</jsp:root>
