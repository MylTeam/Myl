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
			
			<script src="${pageContext.request.contextPath}/scripts/deckselected.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/usuario.js" type="text/javascript"></script>						
							
		 ]]>
</jsp:text>
</head>
<body>
<form style="width: 90%; border: 0px">
	<input type="hidden" name="context" id="context" value="${pageContext.request.contextPath}" />
	
	<table style="width:100%">
	<tr>
	<td style="width: 50%">
	
	<table>
	<tr><td>${usuario.login}</td></tr>
	<tr>	
	<td>Deck predeterminado:</td>
	<td>
	
	<input type="hidden" name="deck" id="deck" value="${usuario.deckPred}" />
	<b><span id="result"> </span></b>
	
	</td>
	</tr>
	</table>
	
	</td>
	<td style="width: 50%">
	<table id="tblDeck" style="width: 100%;" >
		<thead>
			<tr>				
				<th>Nombre</th>
				<th>Predeterminado</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
					<s:iterator value="lista">
						<tr>
							<td style="width: 50%">${deckNombre}
							<input type="hidden" id="h${deckId}" value="${deckNombre}"/>
							</td>
							<td style="text-align: center;"><input type="radio" name="deckpred" onclick="setSelection(this.value)" value="${deckId}"/></td>
							<td style="text-align: center;">
									<a href="${pageContext.request.contextPath}/deck/${deckId}/edit">
									<img height="40" width="40" src="${pageContext.request.contextPath}/images/buttons/editfeather.png" title="Modificar deck"/></a>
							
									<a href="${pageContext.request.contextPath}/deck/${deckId}/deleteConfirm">
									<img height="40" width="40" src="${pageContext.request.contextPath}/images/buttons/elim.png" title="Eliminar deck"/></a>
							</td>
						</tr>
					</s:iterator>
		</tbody>
	</table>
	
	</td></tr>
	</table>
	<div id="btnNew">
	<a href="${pageContext.request.contextPath}/deck/new"><input type="button" value="Nuevo Deck"></input></a>
	</div>
	</form>
</body>
	</html>
</jsp:root>
