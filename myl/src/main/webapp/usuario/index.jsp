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

				<td style="width: 50%; vertical-align: top">

					<table style="width: 100%">
						<tr>
							<td><h2>Bienvenido ${usuario.login}</h2></td>
						</tr>
						<tr>
							<td><input type="hidden" name="deck" id="deck"
								value="${usuario.deckPred}" /> <b>Mazo: <span id="result">
								</span></b></td>
							<td ><b><a id="lkPerfil"
									href="${pageContext.request.contextPath}/usuario/${usuario.idUsuario}/edit">Modificar
										Perfil</a></b></td>
							<td><b><a
									href="${pageContext.request.contextPath}/usuario/${usuario.idUsuario}">Tus
										estadísticas</a></b></td>
						</tr>

					</table>

					<table id="tblDeck" style="width: 100%;">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Formato</th>
								<th id="opcPred" style="text-align: center;">Predeterminado</th>
								<th id="opcAcciones" style="text-align: center;">Acciones</th>
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

				<td id="tipNews" style="width: 50%; height: 100%; text-align: center;"><a
					class="twitter-timeline" href="https://twitter.com/MylOnlineZ"
					data-widget-id="521443826024476672">Tweets por @MylOnlineZ</a></td>
			</tr>
		</table>
		<div id="btnNew">
			<a href="${pageContext.request.contextPath}/deck/new"><input
				type="button" value="Nuevo Mazo"></input></a>
		</div>
	</form>
	
	      <!-- Tip Content -->
    <ol id="joyRideTipContent">
      <li data-id="mnPerfil" data-text="Next" class="custom">
        <h2>Perfil</h2>
        <p>Aquí podrás ver tu información, los mazos que has armado y las noticias de la plataforma.</p>
      </li>
      <li data-id="mnJugar" data-button="Next" data-options="tipLocation:top;tipAnimation:fade">
        <h2>Jugar</h2>
        <p>Podrás acceder a la sala de jugadores en donde podrás charlar y retar a duelo a otros jugadores. Se requiere tener un mazo predeterminado para poder entrar.</p>
      </li>
      <li data-id="mnRanking" data-button="Next" data-options="tipLocation:top;tipAnimation:fade">
        <h2>Ranking</h2>
        <p>Podrás ver las estadísticas de otros jugadores. (En construcción)</p>
      </li>
      <li data-id="lkPerfil" data-button="Next" data-options="tipLocation:right">
        <h2>Stop #3</h2>
        <p>It works right aligned.</p>
      </li>
      <!-- <li data-button="Next">
        <h2>Stop #4</h2>
        <p>It works as a modal too!</p>
      </li>
      <li data-class="someclass" data-button="Next" data-options="tipLocation:right">
        <h2>Stop #4.5</h2>
        <p>It works with classes, and only on the first visible element with that class.</p>
      </li>
      <li data-id="numero5" data-button="Close">
        <h2>Stop #5</h2>
        <p>Now what are you waiting for? Add this to your projects and get the most out of your apps!</p>
      </li> -->
    </ol>
</body>
	</html>
</jsp:root>
