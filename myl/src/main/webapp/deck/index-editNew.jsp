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
			
		 ]]>

</jsp:text>
</head>
<body>
	<s:url id="urlCancelar" value="/usuario" includeContext="true" />
	<s:actionerror id="saeMuseo" theme="jquery" />
	<s:fielderror id="sfeMuseo" theme="jquery" />
	<s:form action="%{#request.contextPath}/deck" method="post" theme="simple" acceptcharset="UTF-8" cssStyle="border: 0px;">


	<table>
	<tr>
	<td></td>
	<td></td>
	<td></td>
	</tr>	
	</table>


	</s:form>
</body>
</html>
</jsp:root>