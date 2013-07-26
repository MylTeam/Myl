var origen;
var destino;
var obj = {
		deck : [],		
		resultado : []
	};


function createCard(c, context, origenPila) {
	var img = document.createElement('img');
	img.alt=c;
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

function drawResult(context){
	$("#collection").empty();
	
	for(var c=0;c<obj["resultado"].length;c++){
		var img=createCard(c, context, "resultado");
		
		var divCollection=document.getElementById("collection");
		divCollection.appendChild(img);
	}
}

function drop(ev)
{
	
destino=ev.target.id;

if(origen=="collection" && destino=="deck1" && ev.target == "[object HTMLDivElement]"){
	var card={
			id:0,
			cant: 0
	};
	
var context = $('#context').val();
ev.preventDefault();
var data=ev.dataTransfer.getData("Text");
var imgAux=document.getElementById(data);

card.id=obj["resultado"][imgAux.alt].id;

/**
 * Verifica si ya existe la carta en el deck
 * si existe incrementa en uno, si no existe la agrega a la lista
 */
var existe=false;
for(var c=0;c<obj["deck"].length;c++){	
	if(obj["deck"][c].id==card.id){
		existe=true;
		obj["deck"][c].cant+=1;
	}
}
if(existe==false){	
	card.cant=1;
	obj["deck"].push(card);
}

var img=createCard(imgAux.alt, context, "resultado");
img.alt=card.id;
img.height="100";
img.id=ev.target.childNodes.length;
ev.target.appendChild(img);
}else if(origen=="deck1" && destino=="trash" && ev.target == "[object HTMLDivElement]"){
	
}

}

function drag(ev)
{    	
ev.dataTransfer.setData("Text",ev.target.id);    
}

function allowDrop(ev)
{
ev.preventDefault();
}

function search() {
	var ediciones=document.getElementById("edicion");
	var frecuencias=document.getElementById("frecuencia");
	var tipos=document.getElementById("tipo");	
	var razas=document.getElementById("raza");
	
	var edicionC=ediciones.options[ediciones.selectedIndex].value;
	var frecuenciaC=frecuencias.options[frecuencias.selectedIndex].value;
	var tipoC=tipos.options[tipos.selectedIndex].value;
	var razaC=razas.options[razas.selectedIndex].value;
		
	var criterioJson={};
	
	if($("#nombre").val()!=""){
	criterioJson['nombre']=$("#nombre").val();
	}
	if(edicionC!=""){
	criterioJson['idEdicion']=edicionC;
	}
	if(frecuenciaC!=""){
	criterioJson['Frecuencia']=frecuenciaC;
	}
	if(tipoC!=""){
	criterioJson['tipo']=tipoC;
	}
	if($("#coste").val()!=""){
	criterioJson['Coste']=parseInt($("#coste").val());
	}
	if($("#fuerza").val()!=""){
	criterioJson['Fuerza']=parseInt($("#fuerza").val());
	}
	if(razaC!=""){
	criterioJson['raza']=razaC;
	}
		
	criterioJson=JSON.stringify(criterioJson);
	
	var context = $('#context').val();
	$.ajax({
		url : context + "/deck!search?criterioJson="+criterioJson,
		type : "POST",
		error : function() {
			alert('Error');
		},
		success : function(data) {
			obj["resultado"] = data.resultado;			
			drawResult(context);
		}
	});

}