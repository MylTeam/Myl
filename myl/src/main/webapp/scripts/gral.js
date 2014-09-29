function notifyUserDisconnected(){
	$( "#dialog-udis" ).dialog({ 
		width: "20%",
		resizable: false,
		title: "Atención",
		buttons: {
			"Cerrar": function () {
			$(this).dialog("close");
			}
		}
	});
}


function notifyNewGame(){
	$( "#dialog-newg" ).dialog({ 
		width: "20%",
		resizable: false,
		title: "Nuevo Juego",
		buttons: {
			"Aceptar": function () {
				$(this).dialog("close");
//				var url="http://"+location.host+$("#hidden").val()+"/chat?user1="+$("#user1").val()+"&user2="+$("#user2").val()+"&format=1";
//				window.location=url;
				location.reload();
			},
			"Cancelar": function (){
				$(this).dialog("close");
				window.close();
			}
	
		}
	});
}


