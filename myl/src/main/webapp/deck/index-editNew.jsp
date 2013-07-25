<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Nuevo Deck</title>
<jsp:text>
	<![CDATA[ 
			<script src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/scripts/deck.js" type="text/javascript"></script>
		 ]]>

</jsp:text>
</head>
<body>
	<s:url id="urlCancelar" value="/usuario" includeContext="true" />
	<s:actionerror id="saeMuseo" theme="jquery" />
	<s:fielderror id="sfeMuseo" theme="jquery" />
	<s:form action="%{#request.contextPath}/deck" method="post" theme="simple" acceptcharset="UTF-8" cssStyle="border: 0px;">
	<input type="hidden" name="context" id="context" value="${pageContext.request.contextPath}"/>

	<table>
	<tr>
	<td style="width: 25%"></td>
		
	<td style="width: 30%"><table>
	<tr><td></td></tr>
	<tr><td><center><img id="viewCard" src="" height="100%"/></center></td></tr>
	</table></td>
	
	<td style="width: 45%">
	<table><tr><td>
	<div id="deck" ondrop="drop(event)" ondragover="wsclient.allowDrop(event)">
	
	</div>
	</td></tr><tr><td>
	
	</td></tr>
	</table>
	</td>
	</tr>	
	</table>


	</s:form>
</body>
</html>
</jsp:root>