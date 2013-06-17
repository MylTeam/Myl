<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
		
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" href="css/default.css?123" rel="stylesheet" />
<link type="text/css" href="css/smoothness/jquery-ui-1.8.22.custom.css"
	rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="js/wsclient.js?2032"></script>
<title>Autentia - Chat con Websockets y Tomcat 7</title>
<script type="text/javascript">
	$(function() {
		$('#conversations').tabs();
		
	});
	
</script>
<style type="text/css">
#div1 {width:700px;height:100px;padding:10px;border:1px solid #aaaaaa;}
#div2 {width:700px;height:100px;padding:10px;border:1px solid #aaaaaa;}
#div3 {width:700px;height:100px;padding:10px;border:1px solid #aaaaaa;}
#div4 {width:700px;height:100px;padding:10px;border:1px solid #aaaaaa;}
</style>

</head>
<body>
	<a href="http://www.autentia.com" target="_blank"><img
		src="img/autentia.png" alt="Autentia" /></a>
	<h1>EJEMPLO DE WEBSOCKETS CON TOMCAT 7</h1>
	<table>
		<tr>
			<td>
				<div id="container">
					<div class="leftPanel">
						<div class="userInfo">
							<span class="disconnected" id="status">Desconectado</span>
							Nombre: <input type="text" id="userName" /><span
								class="onLineUserName"></span>
						</div>
						<div>
							<button id="connect"
								onclick="wsclient.connect(document.getElementById('userName').value);">Conectar</button>
							<button id="disconnect" disabled="disabled"
								onclick="wsclient.disconnect();">DesconexiÃ³n</button>
						</div>
						<div id="onLineUsersPanel">
							<h3>Usuarios conectados:</h3>
							<ul id="onlineUsers">

							</ul>
						</div>
					</div>
					</div>

					<div id="conversations">
						<ul>
						</ul>
					</div>
			</td>
			<td>
			<p>Drag the W3Schools image into the rectangle:</p>

<img id="drag1" src="images/1.jpeg" draggable="true" ondragstart="wsclient.drag(event)" height="70px" width="40px"/>
<img id="drag2" src="images/2.jpeg" draggable="true" ondragstart="wsclient.drag(event)" height="70px" width="40px"/>
<img id="drag3" src="images/3.jpeg" draggable="true" ondragstart="wsclient.drag(event)" height="70px" width="40px"/>
<img id="drag4" src="images/4.jpeg" draggable="true" ondragstart="wsclient.drag(event)" height="70px" width="40px"/>
<img id="drag5" src="images/5.jpeg" draggable="true" ondragstart="wsclient.drag(event)" height="70px" width="40px"/>
</br>
<div id="div1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</br>
<div id="div2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</br>
<div id="div3" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</br>
<div id="div4" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</br>
<input type="hidden" name="user2" id="user2" value=""/>

					
			
			</td>
		</tr>		
	</table>
</body>
</html>
</jsp:root>