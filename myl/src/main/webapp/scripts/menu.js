$(function(){
    $.contextMenu({
        selector: '.deck', 
        callback: function(key, options) {
        	
            switch(key){
            case "draw":
            	drawCard();
            	break;            
            case "view":
            	view("deck1",$('#hidden').val());
            	$( "#dialog" ).dialog();
            	break;            
            case "drop":
            	break;
            }
        },
        items: {
            "draw": {name: "Robar carta"},            
            "view": {name: "Ver castillo"},
            "drop": {name: "Botar"}            
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
            	view("cementerio1",$('#hidden').val());
            	$( "#dialog" ).dialog();
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
            	view("destierro1",$('#hidden').val());
            	$( "#dialog" ).dialog();
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
            	view("remocion1",$('#hidden').val());
            	$( "#dialog" ).dialog();
            	break;
            }
        },
        items: {                       
            "view": {name: "Ver cartas removidas"}            
        }
    });              
});

