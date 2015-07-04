$(document).ready(function() {
	$('#tblEdicion').dataTable({
		"bJQueryUI" : true,
		"bPaginate" : true,
		"bInfo" : false,

	// "sPaginationType" : "full_numbers",
	// "sDom" : '<"H"Tfr>t<"F"ip>',
	// "oTableTools" : {
	// "aButtons" : [ "copy", "csv", "xls", "pdf", {
	// "sExtends" : "collection",
	// "sButtonText" : "Save",
	// "aButtons" : [ "csv", "xls", "pdf" ]
	// } ]
	// }
	});
	$("#tblUsers").css("border-spacing", "0");
	$("#tblUsers_filter").removeClass("dataTables_filter");

});



function addEdition() {

	$("#divAddEdition").html("");
	$.ajax({
		type : "post",
		url : $("#hdnRutaContexto").val() + "/edicion!editNew",
		data : null,
		beforeSend : bloquearUI,
		async : true,
		success : function(data) {
			$("#divAddEdition").html(data);
			
			$("#dlgAddEdition").show();
			$.publish("openDialogAddEdition");
			$.unblockUI();

		},
		error : function(data) {
			console.log("Error en la petición");
		}
	});

}


function sendFormEdition() {
	var ruta = $("#hdnRutaContexto").val() + "/edicion!create";

	$.when(sendForm(ruta, "frmAddEdition")).done(
			function(data) {
				console.log(data);
				$("#divAddEdition").html(data);
				if ($("#hdnErrores").val() == "false") {
					var mensaje = $("#ulAMAddEdition");
					var actionMessages = $("#ulAMAddEdition").html();

					if (mensaje !== undefined) {
						mensaje.remove();
					}

					$("#frmIndexEdition").prepend(
							"<ul id='ulAMAddEdition' class='success-message'>"
									+ actionMessages + "</ul>");
					$("#divAddEdition").html("");
					reloadEditions();
					closeDialogAddEdition();
				}
				$.unblockUI();
			});
}

function closeDialogAddEdition(){
	$.publish("closeDialogAddEdition");
}

function reloadEditions() {
	$.publish("reloadEditions");
}