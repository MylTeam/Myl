<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0" xmlns:s="/struts-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link type="text/css" href="${pageContext.request.contextPath}/Estilos/default.css?123" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/Estilos/smoothness/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
<jsp:text>
	<![CDATA[ 
			<script src="${pageContext.request.contextPath}/scripts/wsclient.js?2032" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/jquery-ui-1.8.22.custom.min.js" type="text/javascript"></script>		
		 ]]>
</jsp:text>
 
<title>Autentia - Chat con Websockets y Tomcat 7</title>
 
<script type="text/javascript">
	$(function() {
		$('#conversations').tabs();
		
	});
	
</script>

<style type="text/css">
#mano1 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
#mano2 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
#apoyo1 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
#apoyo2 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
#defensa1 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
#defensa2 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
#ataque1 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
#ataque2 {width:700px;height:100%;padding:10px;border:1px solid #aaaaaa;}
</style>

</head>
<body>	
	<!--  <h1>WEBSOCKETS CON TOMCAT 7</h1>-->
	
<table id="Table1" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
  <tbody>
    <tr style="HEIGHT: 50%;">
      <td style="WIDTH: 25%;">
        <table id="Ctrl1" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
          <tbody>
            <tr>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td></td>
            </tr>
          </tbody>
        </table></td>
      <td style="WIDTH: 50%;">
        <table id="P1" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
          <tbody>
            <tr>
              <td><div id="mano1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="apoyo1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="defensa1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="ataque1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
          </tbody>
        </table></td>
      <td id="info" style="WIDTH: 25%;"></td>
    </tr>
    <tr style="HEIGHT: 50%;">
      <td id="chat" style="WIDTH: 25%;">
      	<table>
			<tr><td>
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
					</td><td>
					<div id="conversations">
						<ul>
						</ul>
					</div></td></tr></table>
      </td>
      <td style="WIDTH: 50%;">
        <table id="P2" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
          <tbody>
            <tr>
              <td><div id="ataque2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="defensa2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="apoyo2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="mano2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)">
              
              <s:iterator value="deck1">
				<img id="d${idTemp}${edicion.siglas}${numero}" src="${pageContext.request.contextPath}/images/myl/esp/${numero}.jpg" draggable="true" ondragstart="wsclient.drag(event)" height="70px" width="40px"/>
				</s:iterator>
              
              </div></td>
            </tr>
          </tbody>
        </table></td>
      <td style="WIDTH: 25%;">
        <table id="Ctrl2" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
          <tbody>
            <tr>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td></td>
              <td></td>
            </tr>
          </tbody>
        </table></td>
    </tr>
  </tbody>
</table>
	

<!-- 	
	<table>
		<tr>
			<td>

			</td>
			<td>
			<p>Drag the W3Schools image into the rectangle:</p>

<s:iterator value="deck1">
	<img id="drag${numero}" src="${pageContext.request.contextPath}/images/myl/esp/${numero}.jpg" draggable="true" ondragstart="wsclient.drag(event)" height="70px" width="40px"/>
</s:iterator>

<table>
<tr><td>
<div id="div1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr><tr><td>
<div id="div2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr><tr><td>
<div id="div3" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr><tr><td>
<div id="div4" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr><tr><td>
<div id="div5" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr><tr><td>
<div id="div6" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr><tr><td>
<div id="div7" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr><tr><td>
<div id="div8" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div>
</td></tr>
</table>

				
			
			</td>
		</tr>		
	</table>
	 -->
	 <input type="hidden" name="user2" id="user2" value=""/>
</body>
</html>
</jsp:root>
