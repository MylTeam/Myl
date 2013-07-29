$(function(){
    $.contextMenu({
        selector: '.deck', 
        callback: function(key, options) {
        	
            switch(key){
            case "draw":
            	drawCard();
            	break;
            case "drawHand":
            	drawHand($("#hidden").val());
            	break;
            case "view":
            	$("#dialog").attr("name","deck1");
            	view("deck1",$('#hidden').val());
            	$( "#dialog" ).dialog({ 
            		width: 500,
            		title: "Castillo" });
            	break;            
            case "dropx":
            	$("#cantidad").val("0");
            	$("#dialog-cant").dialog({
            			width: 300,
            			title: "Botar",
            			buttons: {
            				"Aceptar":function(){            					
            					dropxcards($("#cantidad").val());
            					$( this ).dialog( "close" );
            				},
            				"Cancelar":function() {
            			          $( this ).dialog( "close" );
            		        }
            			}
            	});
            	break;
            case "viewx":
            	$("#cantidad").val("0");
            	$("#dialog-cant").dialog({
            			width: 300,
            			title: "Ver",
            			buttons: {
            				"Aceptar":function(){
            					$( this ).dialog( "close" );
            					viewx("deck1",$('#hidden').val(),$("#cantidad").val());
            					$( "#dialog" ).dialog({             						
            						width: 500,
            						title: "Primeras "+$("#cantidad").val()+" cartas." });
            				},
            				"Cancelar":function() {
            			          $( this ).dialog( "close" );
            		        }
            			}
            	});
            	break;
            case "shuffle":
            		shuffle();
            	break;
            }
        },
        items: {
            "draw": {name: "Robar carta"},
            "drawHand": {name: "Robar mano"},
            "view": {name: "Ver castillo"},
            "dropx": {name: "Botar X"},
            "viewx": {name: "Ver X"},
            "shuffle": {name: "Barajar"}
        }
    });
    
//    $('.deck').on('click', function(e){    	
//    })       
});

$(function(){
    $.contextMenu({
        selector: '.cementerio', 
        callback: function(key, options) {
        	
            switch(key){            
            case "view":
            	$("#dialog").attr("name","cementerio1");
            	view("cementerio1",$('#hidden').val());
            	$( "#dialog" ).dialog({ 
            		width: 500,
            		title: "Cementerio" });
            	break;
            }
        },
        items: {                       
            "view": {name: "Ver cementerio"}            
        }
    });              
});

$(function(){
    $.contextMenu({
        selector: '.destierro', 
        callback: function(key, options) {
        	
            switch(key){            
            case "view":
            	$("#dialog").attr("name","destierro1");
            	view("destierro1",$('#hidden').val());
            	$( "#dialog" ).dialog({ 
            		width: 500,
            		title: "Destierro" });
            	break;
            }
        },
        items: {                       
            "view": {name: "Ver destierro"}            
        }
    });              
});

$(function(){
    $.contextMenu({
        selector: '.remocion', 
        callback: function(key, options) {
        	
            switch(key){            
            case "view":
            	$("#dialog").attr("name","remocion1");
            	view("remocion1",$('#hidden').val());
            	$( "#dialog" ).dialog({ 
            		width: 500,
            		title: "Cartas removidas" });
            	break;
            }
        },
        items: {                       
            "view": {name: "Ver cartas removidas"}            
        }
    });              
});

$(function(){
    $.contextMenu({
        selector: '.cardright', 
        callback: function(key, options) {
        	
            switch(key){            
            case "bottom":
            	var cardbottom=$("#"+options.$trigger.context.id);
            	var parent=cardbottom.parent().attr("id");            	
            	tobottom(cardbottom, parent);            	            	
            	break;
            }
        },
        items: {                       
            "bottom": {name: "Enviar al fondo del castillo."}            
        }
    });              
});

$(function(){
    $.contextMenu({
        selector: '.opcementerio', 
        callback: function(key, options) {        
            switch(key){            
            case "view":            	
            	viewop("cementerio1",$('#hidden').val());
            	$( "#dialog" ).dialog({ 
            		width: 500,
            		title: "Cementerio oponente" });
            	break;
            }
        },
        items: {                       
            "view": {name: "Ver cementerio oponente"}            
        }
    });              
});
$(function(){
    $.contextMenu({
        selector: '.opdestierro', 
        callback: function(key, options) {        
            switch(key){            
            case "view":            	
            	viewop("destierro1",$('#hidden').val());
            	$( "#dialog" ).dialog({ 
            		width: 500,
            		title: "Destierro oponente" });
            	break;
            }
        },
        items: {                       
            "view": {name: "Ver destierro oponente"}            
        }
    });              
});
$(function(){
    $.contextMenu({
        selector: '.opremocion', 
        callback: function(key, options) {        
            switch(key){            
            case "view":            	
            	viewop("remocion1",$('#hidden').val());
            	$( "#dialog" ).dialog({ 
            		width: 500,
            		title: "Remoción oponente" });
            	break;
            }
        },
        items: {                       
            "view": {name: "Ver cartas removidas"}            
        }
    });              
});