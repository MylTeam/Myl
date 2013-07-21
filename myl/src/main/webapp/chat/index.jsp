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
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/jquery-ui-1.8.22.custom.min.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/wsclient.js?2032" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/game.js" type="text/javascript"></script>
					
		 ]]>
</jsp:text>
 
<title>Duel Room</title>
 <script type="text/javascript">
	$(function() {
		$('#conversations').tabs();					  
	});	
	</script>	
<style type="text/css">
#mano1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#mano2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#apoyo1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#apoyo2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#defensa1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#defensa2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#ataque1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#ataque2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}

#oropagado2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#remocion2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#castillo2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#cementerio2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#reserva2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#destierro2 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}

#oropagado1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#remocion1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#castillo1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#cementerio1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#reserva1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}
#destierro1 {width:100%;height:100%;padding:5px;border:1px solid #aaaaaa;overflow:auto;}

</style>

</head>
<body>	

<input type="hidden" name="hidden" id="hidden" value="${pageContext.request.contextPath}"/>
	
<table id="Table1" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
  <tbody>
    <tr style="HEIGHT: 50%;">
      <td style="WIDTH: 25%;">
        <table id="Ctrl1" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
          <tbody>
            <tr>
              <td style="WIDTH: 50%"><div id="oropagado2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
              <td style="WIDTH: 50%"><div id="remocion2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="castillo2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
              <td><div id="cementerio2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="reserva2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
              <td><div id="destierro2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
          </tbody>
        </table></td>
      <td style="WIDTH: 50%;">
        <table id="P1" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
          <tbody>
            <tr style="HEIGHT: 70px;">
              <td><div id="mano2" align="center" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="apoyo2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="defensa2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="ataque2" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
          </tbody>
        </table></td>
      <td id="info" style="WIDTH: 25%;">
      <center><img id="viewCard" src="" draggable="true" height="100%"/></center>
      </td>
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
              <td><div id="ataque1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="defensa1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="apoyo1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr style="HEIGHT: 70px;">
              <td><div id="mano1" align="center" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)">
                            
              </div></td>
            </tr>
          </tbody>
        </table></td>
      <td style="WIDTH: 25%;">
        <table id="Ctrl2" class="Table" style="HEIGHT: 100%; WIDTH: 100%">
          <tbody>
            <tr>
              <td style="WIDTH: 50%"><div id="oropagado1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
              <td style="WIDTH: 50%"><div id="remocion1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="castillo1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)">
              <img id="myldeck1" name="myldeck1" src="${pageContext.request.contextPath}/images/myl/myldeck.jpg" ondblclick="drawCard()" draggable="true" height="70" ondragstart="wsclient.drag(event)"/>
              </div></td>
              <td><div id="cementerio1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
            <tr>
              <td><div id="reserva1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
              <td><div id="destierro1" ondrop="wsclient.drop(event)" ondragover="wsclient.allowDrop(event)"></div></td>
            </tr>
          </tbody>
        </table></td>
    </tr>
  </tbody>
</table>
	

	 <input type="hidden" name="user2" id="user2" value=""/>
</body>
</html>
</jsp:root>
