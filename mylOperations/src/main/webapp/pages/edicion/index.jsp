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
			<script src="${pageContext.request.contextPath}/pages/usuario/js/usuario.js" type="text/javascript"></script>
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

<h2>Bienvenido</h2>


	</form>
</body>
	</html>
</jsp:root>
