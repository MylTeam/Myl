var origen;
var obj = {
		deck : [],		
		resultado : []
	};

function createCard(c, context, origenPila) {
	var img = document.createElement('img');
	img.id = obj[origenPila][c].idTemp;
	img.name = obj[origenPila][c].numero;	
	img.src = context + "/images/myl/"+obj[origenPila][c].siglas+"/" + obj[origenPila][c].numero + ".jpg";
	img.className=obj[origenPila][c].tipo;
	img.draggable = "true";
	img.height = "70";
	img.addEventListener('dragstart', function drag(ev) {		
		origen = ev.target.parentNode.id		
		ev.dataTransfer.setData("Text", ev.target.id);
	}, false);
	img.onmouseover = function showImage(ev) {
		var viewCard = document.getElementById("viewCard");
		viewCard.src = context + "/images/myl/"+obj[origenPila][c].siglas+"/" + ev.target.name + ".jpg";
	}
	return img;
}

function search() {		
	var context = $('#hidden').val();
	$.ajax({
		url : context + "/deck!search",
		type : "POST",
		error : function() {
			alert('Error');
		},
		success : function(data) {
			obj["resultado"] = data.resultado;			
		}
	});

}

function drag(ev)
{    	
ev.dataTransfer.setData("Text",ev.target.id);    
}

function drop(ev)
{
var context = $('#context').val();
ev.preventDefault();
var data=ev.dataTransfer.getData("Text");
ev.target.appendChild(document.getElementById(data));
}

function allowDrop(ev)
{
ev.preventDefault();
}