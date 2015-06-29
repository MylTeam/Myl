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
	<s:url id="urlCancelar" value="/usuario" includeContext="true" />
	<s:actionerror id="saeUsuario" theme="jquery" />
	<s:fielderror id="sfeUsuario" theme="jquery" />
	<s:actionmessage id="samUsuario" theme="jquery" />
	<s:form action="%{#request.contextPath}/edicion/" method="post" theme="simple" acceptcharset="UTF-8" cssStyle="border: 0px;" enctype="multipart/form-data">


		<center>
			<h1>Agregar Edición</h1>
		</center>

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
				<td><label>Spoiler:</label></td>
				<td><s:file id="spoiler" name="archivoSpoiler" /></td>
			</tr>
			<tr>
				<td><label>Imagenes:</label></td>
				<td><s:file id="images" name="archivoImagen" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><sj:submit
						id="btnAceptar" value="Aceptar" button="true"
						buttonIcon="ui-icon-star" /> <sj:a id="btnCancelar" button="true"
						href="#" onclick="location.href='%{urlCancelar}'">Cancelar</sj:a></td>
			</tr>
		</table>
	</s:form>

</body>
	</html>
</jsp:root>