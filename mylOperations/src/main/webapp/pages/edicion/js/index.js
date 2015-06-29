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
			console.log(data);
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

function closeDialogAddEdition(){
	$.publish("closeDialogAddEdition");
}