$(document).ready(function() {
	var selection=$("#deck").val();
	$("input[name=deckpred][value=" + selection + "]").prop('checked', true);
	$("#result").val($("#h"+selection).val());
	
});


function setSelection(selection)
{
$("#result").val($("#h"+selection).val());
	
$.ajax({
	url : $("#context").val() + "/usuario!setDeckPredeterminado?deckId=" + selection,
	type : "POST",
	error : function() {
		alert('Error');
	},
	success : function(data) {		
	}
});

}
