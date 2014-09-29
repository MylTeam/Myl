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
				wsclient.toChat(document.getElementById("user1").value, document.getElementById("user2").value, "gamereadyaccept");
				$(this).dialog("close");				
				location.reload();
			},
			"Cancelar": function (){
				$(this).dialog("close");
				window.close();
			}
	
		}
	});
}

function newGame(){
	location.reload();
}


