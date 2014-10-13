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
		 	<script src="${pageContext.request.contextPath}/usuario/js/facebook.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/usuario/js/deckselected.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/usuario/js/usuario.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/usuario/js/twitter.js" type="text/javascript"></script>
			
								
		 ]]>
</jsp:text>
</head>
<body>
	<s:actionerror id="saeUsuario" theme="jquery" />
	<s:fielderror id="sfeUsuario" theme="jquery" />
	<s:actionmessage id="samUsuario" theme="jquery" />




	<form style="width: 90%; border: 0px">
		<input type="hidden" name="context" id="context"
			value="${pageContext.request.contextPath}" />

		<table style="width: 100%">
			<tr>
				<td style="width: 20%;">
					<div id="fb-root">
						<div class="fb-like-box"
							data-href="https://www.facebook.com/mylzonline" data-height="400"
							data-colorscheme="dark" data-show-faces="false"
							data-header="false" data-stream="true" data-show-border="true"></div>
					</div>
				</td>
				<td style="width: 50%; vertical-align: top">
				
					<table>
						<tr>
							<td>Bienvenido ${usuario.login}</td>
							</tr><tr>
							<td><input type="hidden" name="deck" id="deck"
								value="${usuario.deckPred}" /> <b>Mazo: <span id="result">
								</span></b></td>
								</tr><tr>
							<td><a
								href="${pageContext.request.contextPath}/usuario/${usuario.idUsuario}/edit">Modificar
									Perfil</a></td>
						</tr>

					</table>
					
					<table id="tblDeck" style="width: 100%;">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Formato</th>
								<th style="text-align: center;">Predeterminado</th>
								<th style="text-align: center;">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="lista">
								<tr>
									<td>${deckNombre} <input type="hidden" id="h${deckId}"
										value="${deckNombre}" />
									</td>
									<td style="width: 15%">${formato.nombre}</td>
									<td style="text-align: center; width: 18%"><input
										id="deckpred" type="radio" name="deckpred"
										onclick="setSelection(this.value)" value="${deckId}" /></td>
									<td style="text-align: center; width: 18%"><a
										href="${pageContext.request.contextPath}/deck/${deckId}/edit">
											<img height="40" width="40"
											src="${pageContext.request.contextPath}/images/buttons/editfeather.png"
											title="Modificar deck" />
									</a> <a
										href="${pageContext.request.contextPath}/deck/${deckId}/deleteConfirm">
											<img height="40" width="40"
											src="${pageContext.request.contextPath}/images/buttons/elim.png"
											title="Eliminar deck" />
									</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

				</td>

				<td style="width: 20%; height: 100%;"><a
					class="twitter-timeline" href="https://twitter.com/MylOnlineZ"
					data-widget-id="521443826024476672">Tweets por @MylOnlineZ</a></td>
			</tr>
		</table>
		<div id="btnNew">
			<a href="${pageContext.request.contextPath}/deck/new"><input
				type="button" value="Nuevo Mazo"></input></a>
		</div>
	</form>
</body>
	</html>
</jsp:root>
