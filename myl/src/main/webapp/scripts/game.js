var origen;
var destino;

var obj={deck1:[],mano1:[],apoyo1:[],defensa1:[],ataque1:[],reserva1:[]};

$(document).ready(function() {
	var context = $('#hidden').val();
	$.ajax({
		url : context + "/chat!prueba",
		type : "POST",
		error : function() {
			alert('Error');
		},
		success : function(data) {
			obj["deck1"] = data.deck1;
			obj["deck1"].sort(function() {
				return Math.random() - 0.5
			});
			drawHand(context);
		}
	});

});

function drawHand(context) {
	var divMano = document.getElementById("mano1");	
	for ( var c = 0; c < 8; c++) {
		drawCard();
	}
}

function drawCard(){
	var context = $('#hidden').val();
	var divMano = document.getElementById("mano1");
	var img = createCard(0, context);
	divMano.appendChild(img);
	obj["mano1"].unshift(obj["deck1"].splice(0, 1)[0]);
}

function createCard(c, context) {
	var img = document.createElement('img');
	img.id = obj["deck1"][c].idTemp;
	img.name = obj["deck1"][c].numero;
	img.src = context + "/images/myl/esp/" + obj["deck1"][c].numero + ".jpg";
	img.draggable = "true";
	img.height = "70";
	img.width = "40";
	img.addEventListener('dragstart', function drag(ev) {
		origen=ev.target.parentNode.id		
		ev.dataTransfer.setData("Text", ev.target.id);
	}, false);
	img.onmouseover = function showImage(ev) {
		var viewCard = document.getElementById("viewCard");
		viewCard.src = context + "/images/myl/esp/" + ev.target.name + ".jpg";
	}
	return img;
}

function dropCard(ev) {
	ev.preventDefault();
	var context = $('#hidden').val();
	var data = ev.dataTransfer.getData("Text");

	// Verifica si la carta se esta moviendo hacia un div o hacia el deck
	if (ev.target == "[object HTMLDivElement]" || ev.target.id == "myldeck1") {
		
		// si la carta arrastrada no es el deck relocaliza la carta		
		if (data != "myldeck1") {
			if (ev.target.id != "myldeck1") {
				ev.target.appendChild(document.getElementById(data));				
				destino= ev.target.id;
				changeZone(obj[origen], obj[destino], data);
				
			} else if (ev.target.id == "myldeck1") {				
				move(obj[origen], obj["deck1"], data);
			}
		} else {
			// si se arrastra desde el deck se crea una carta en la zonaseleccionada
			var img = createCard(0, context);
			destino=ev.target.id;
			
			obj[destino].unshift(obj["deck1"].splice(0,1)[0]);			
			ev.target.appendChild(img);
		}
	}
}

function move(arrayAux,arrayDest,data){
	for(var c=0;c<arrayAux.length;c++){
		if(arrayAux[c].idTemp==data){
			arrayDest.unshift(arrayAux.splice(c,1)[0]);
			$('#'+data).remove();
		}
	}
}

function changeZone(arrayAux,arrayDest,data){
	for(var c=0;c<arrayAux.length;c++){
		if(arrayAux[c].idTemp==data){
			arrayDest.unshift(arrayAux.splice(c,1)[0]);
		}
	}
}