<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Agregar Edición</title>

</head>
<body>
	<s:actionerror id="ulAEAddEdition" theme="jquery" />
	<s:fielderror id="ulFEAddEdition" theme="jquery" />
	<s:actionmessage id="ulAMAddEdition" theme="jquery"/>
	<s:form id="frmAddEdition" namespace="/edicion" method="post"
		theme="simple" acceptcharset="UTF-8" cssStyle="border: 0px;">

		<s:hidden name="errores" id="hdnErrores" />
		<table>
			<tr>
				<td><label>Nombre:</label></td>
				<td><s:textfield id="nombre" name="model.nombre" maxlength="80" /></td>
			</tr>
			<tr>
				<td><label>Siglas:</label></td>
				<td><s:textfield id="siglas" name="model.siglas" maxlength="80" /></td>
			</tr>

			<tr>
				<td colspan="2" style="text-align: center;"> 
						<sj:a id="btnAceptar" button="true"
						href="#" onclick="sendFormEdition()">Aceptar</sj:a>
						
						<sj:a id="btnCancelar" button="true"
						href="#" onclick="closeDialogAddEdition()">Cancelar</sj:a></td>
			</tr>
		</table>
	</s:form>

</body>
	</html>
</jsp:root>