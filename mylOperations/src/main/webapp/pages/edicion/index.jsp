<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Gestión de Ediciones</title>
</head>
<body>
	<s:actionerror id="saeEdicion" theme="jquery" />
	<s:fielderror id="sfeEdicion" theme="jquery" />
	<s:actionmessage id="samEdicion" theme="jquery" />


	<form style="width: 90%; border: 0px" id="frmIndexEdition">
		<input type="hidden" name="context" id="context"
			value="${pageContext.request.contextPath}" />

		<center>

			<s:url var="urlEdiciones" value="/edicion!loadTable" />
			<sj:div href="%{urlEdiciones}" id="divEdicionesTbl"
				reloadTopics="reloadEditions"
				>
				<div class="buttons">
					<br /> <img
						src="${pageContext.request.contextPath }/images/buttons/loading.gif" />
				</div>
			</sj:div>

		</center>

	</form>
	
	<sj:dialog id="dlgAddEdition"
		title="Agregar Edición" autoOpen="false" modal="true"
		showEffect="drop" hideEffect="drop"
		openTopics="openDialogAddEdition" closeTopics="closeDialogAddEdition" width="700">
		<div id="divAddEdition">
			<!--  -->
		</div>
	</sj:dialog>
</body>
	</html>
</jsp:root>
