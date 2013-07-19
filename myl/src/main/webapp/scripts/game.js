var deck1; 

$(document).ready(function() {
	var context=$('#hidden').val();
	$.ajax({
		  url: context+"/chat!prueba",
		  type: "POST",
			       error: function(){  
			           alert('Error');
			       },
			       success: function(data){				    	   				        
			    	   deck1=data.deck1;
			    	   deck1.sort(function() {return Math.random() - 0.5});
			        drawHand(context);
			       }
			   });
	
});

function drawHand(context){
	 var divMano=document.getElementById("mano2");
        var c=0;
		for(var c=0;c<8;c++){	
        var img=createCard(0,context);   		

        divMano.appendChild(img);
        deck1.splice(0,1);               
		}
}

function createCard(c,context){
	var img=document.createElement('img');
    img.id=deck1[c].idTemp;
    img.name=deck1[c].numero;
    img.src=context+"/images/myl/esp/"+deck1[c].numero+".jpg";
    img.draggable="true";               
    img.height="70";
    img.width="40";
    img.addEventListener('dragstart', function drag(ev){ev.dataTransfer.setData("Text",ev.target.id);}, false);
    img.onmouseover=function showImage(ev){
    	var viewCard=document.getElementById("viewCard");        	
    		viewCard.src=context+"/images/myl/esp/"+ev.target.name+".jpg";
    }
    return img;
}

