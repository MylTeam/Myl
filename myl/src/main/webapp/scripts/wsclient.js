var wsclient = (function() {

    var ws = null;
    var wsURI = 'ws://' + location.host  + '/myl/chatws';
    function connect(userName) {    	
    	
        if(!userName || userName == '') {
            return;
        }

        if ('WebSocket' in window) {
            ws = new WebSocket(wsURI + '?userName=' + userName);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(wsURI + '?userName=' + userName);
        } else {
            alert('Tu navegador no soporta WebSockets');
            return;
        }
        ws.onopen = function () {
            setConnected(true);
        };
        ws.onmessage = function (event) {
            var message = JSON.parse(event.data);
            processMessage(message);
        };

        ws.onclose = function () {
            setConnected(false);
            document.getElementById('userName').value = '';
            closeAllConversations();
        };

        function processMessage(message) {
            if (message.messageInfo) {
                showConversation(message.messageInfo.from);
                if(message.messageInfo.message!="gameready" && message.messageInfo.message!="gamereadyok"){
                	addMessage(message.messageInfo.from, message.messageInfo.message, cleanWhitespaces(message.messageInfo.from) + 'conversation');
                }else if(message.messageInfo.message=="gameready"){
                	toChat(document.getElementById("user1").value, document.getElementById("user2").value, "gamereadyok");
                	showConversation(document.getElementById("user2").value);
                }else if(message.messageInfo.message=="gamereadyok"){
                	showConversation(document.getElementById("user2").value);
                }
            } else if (message.statusInfo) {
                if (message.statusInfo.status == 'CONNECTED') {
                    addOnlineUser(message.statusInfo.user);
                } else if (message.statusInfo.status == 'DISCONNECTED') {
                    removeOnlineUser(message.statusInfo.user);
                }
            } else if (message.connectionInfo) {
                var activeUsers = message.connectionInfo.activeUsers;
                for (var i=0; i<activeUsers.length; i++) {
                    addOnlineUser(activeUsers[i]);
                }
            } else if (message.cardInfo){
            	addMessageCard(message.cardInfo.from, message.cardInfo.message, cleanWhitespaces(message.cardInfo.from) + 'conversation');
            	processCard(message.cardInfo.from, message.cardInfo.message, message.cardInfo.carta,message.cardInfo.origen,message.cardInfo.destino);
            }
        }
    }

    function disconnect() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        setConnected(false);
    }

    function setConnected(connected) {
//        document.getElementById('connect').disabled = connected;
//        document.getElementById('disconnect').disabled = !connected;
        cleanConnectedUsers();
        if (connected) {
            updateUserConnected();
        } else {
            updateUserDisconnected();
        }
    }

    function updateUserConnected() {
        var inputUsername = $('#userName');
        var onLineUserName = $('.onLineUserName');
        onLineUserName.html(inputUsername.val());
        inputUsername.css({display:'none'});
        onLineUserName.css({visibility:'visible'});
        $('#status').html('Conectado');
        $('#status').attr({class : 'connected'});
        $('#onLineUsersPanel').css({visibility:'visible'});
        
        toChat(document.getElementById("user1").value, document.getElementById("user2").value, "gameready");
//        showConversation(document.getElementById("user2").value);
    }

    function updateUserDisconnected() {
        $('.onLineUserName').css({visibility:'hidden'});
        $('#userName').css({display:''});
        $('#status').html('Desconectado');
        $('#status').attr({class : 'disconnected'});
        $('#onLineUsersPanel').css({visibility:'hidden'});
    }

    function cleanConnectedUsers() {
        $('#onlineUsers').html('');
    }

    function removeTab(conversationId) {
        $('#conversations').tabs('remove', conversationId);
    }

    function cleanWhitespaces(text) {
        return text.replace(/\s/g,"_");
    }

    function showConversation(from) {
        var conversations = $('#conversations');
        conversations.css({visibility:'visible'});
        var conversationId = cleanWhitespaces(from) + 'conversation';
        if(document.getElementById(conversationId) == null) {
            createConversationPanel(from);
            conversations.tabs('add', '#' + conversationId, from);
        }
        conversations.tabs('select', '#' + conversationId);
        $('#'+conversationId+'message').focus();
    }

    function createConversationPanel(name) {
        var conversationId = cleanWhitespaces(name) + 'conversation';
        var conversationPanel = $(document.createElement('div'));
        conversationPanel.attr({id : conversationId, class : 'conversation'});
        $('<p class="messages"></p><textarea id="' + conversationId + 'message" rows="3"></textarea>').appendTo(conversationPanel);
        var sendButton = createSendButton(name);
        sendButton.appendTo(conversationPanel);
//        var closeButton = createCloseButton(cleanWhitespaces(name));
//        closeButton.appendTo(conversationPanel);
        conversationPanel.appendTo($('#conversations'));      
    }

    function createSendButton(name) {
        var conversationId = cleanWhitespaces(name) + 'conversation';
        var button = $(document.createElement('button'));
        button.html('Enviar');
        button.click(function () {
            var from = document.getElementById('user1').value;
            var message = document.getElementById(conversationId+'message').value;
            toChat(from, name, message);
            addMessage(from, message, conversationId);
            document.getElementById(conversationId+'message').value = '';
        });
        return button;
    }

    function closeAllConversations() {
        for (var i = $('#conversations').tabs('length'); i >= 0; i--) {
            $('#conversations').tabs('remove', i-1);
        }
        $('#conversations').css({visibility : 'hidden'});
    }

    function createCloseButton(conversationId) {
        var button = $(document.createElement('button'));
        button.html('Cerrar');
        button.click(function () {
            removeTab(conversationId);
        });
        return button;
    }

    function addMessage (from, message, conversationPanelId) {
        var messages = $('#' + conversationPanelId + ' .messages');
        $('<div class="message"><span><b>' + from + '</b> dice:</span><p>' + $('<p/>').text(message).html() + '</p></div>').appendTo(messages);
        messages.scrollTop(messages[0].scrollHeight);
        $('#'+conversationPanelId+' textarea').focus();
    }
    
    function addMessageCard(from, message, conversationPanelId) {
        var messages = $('#' + conversationPanelId + ' .messages');
        $('<div class="message">'+from+" :"+message+'</div>').appendTo(messages);
        messages.scrollTop(messages[0].scrollHeight);
        $('#'+conversationPanelId+' textarea').focus();
    }

    function toChat(sender, receiver, message) {    	
        ws.send(JSON.stringify({messageInfo : {from : sender, to : receiver, message : message}}));
    }
    
    function toChatCard(sender, receiver, message, card, origen, destino) {
    	var conversationId = cleanWhitespaces(receiver) + 'conversation';
    	addMessageCard(sender, message, conversationId);
        ws.send(JSON.stringify({cardInfo : {from : sender, to : receiver, message : message, carta : card, origen: origen, destino : destino}}));
    }

    /********* usuarios conectados *******/
    function addOnlineUser(userName) {
        var newOnlineUser = createOnlineUser(userName);
        newOnlineUser.appendTo($('#onlineUsers'));
    }

    function removeOnlineUser(userName) {
        $('#onlineUsers > li').each(function (index, elem) {
            if (elem.id == userName + 'onlineuser') {
                $(elem).remove();
            }
        });
    }

    function createOnlineUser(userName) {
        var link = $(document.createElement('a'));
        link.html(userName);
        link.click(function(){
        	document.getElementById("user2").value=userName;
            showConversation(userName);
            
        });
        var li = $(document.createElement('li'));
        li.attr({id : (userName + 'onlineuser')});
        link.appendTo(li);
        return li;
    }
    
    function allowDrop(ev)
    {
    ev.preventDefault();
    }

    function drag(ev)
    {    	
    origen = ev.target.id;
    ev.dataTransfer.setData("Text",ev.target.id);    
    }
       
    function drop(ev)
    {       	
    var card=dropCard(ev);

    if(card!=null){
//    var from=document.getElementById("userName").value;
    var from=document.getElementById("user1").value;
    var to=document.getElementById("user2").value;
//    var msg="Estoy moviendo "+data+" hacia "+ev.target.id;
    var data = ev.dataTransfer.getData("Text");
    var msg="Moviendo carta hacia "+ev.target.id.replace("1","");
    
//    var conversationId = cleanWhitespaces(to) + 'conversation';
    toChatCard(from, to, msg, card["carta"], card["origen"], card["destino"] );
//    addMessage(from, msg, conversationId);
//    document.getElementById(conversationId+'message').value = '';
    }
    	
    }
    
    
    // metodos publicos
    return {
        connect : connect,
        disconnect : disconnect,
        toChat: toChat,
        toChatCard: toChatCard,
        addMessage: addMessage,
        allowDrop : allowDrop,
        drag : drag,
        drop : drop
    };
   
    //prueba
    //Otra prueba

})();

function processCard(from,message,card,origen,destino){
	var context=$('#hidden').val();
	if(origen!="deck1" && destino!="mano1"){
		card.idTemp="op"+card.idTemp;		
	}else if(origen=="deck1" && destino!="mano1"){
		card.idTemp="op"+card.idTemp;		
	}else if(origen!="deck1" && destino=="mano1"){
		card.idTemp="op"+card.idTemp;		
	}
	
	//si el destino es el campo
	if(destino!="deck1" && destino!="mano1" && destino!="cementerio1" && destino!="destierro1" && destino!="remocion1"){
		// si el origen es desde el campo		
		if(origen!="mano1" && origen!="deck1" && origen!="cementerio1" && origen!="destierro1" && origen!="remocion1" && origen.indexOf(2)==-1){			
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){
					objOp[origen].splice(c,1);					
					$("#"+card.idTemp).remove();
				}
			}
		}else if(origen=="cementerio1" || origen=="destierro1" || origen=="remocion1"){
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){
					objOp[origen].splice(c,1);					
				}
			}
			
			var d = document.getElementById(origen.replace("1","2"));
			if (objOp[origen].length != 0) {
				d.src = context + "/images/myl/"+objOp[origen][0].siglas+"/" + objOp[origen][0].numero+ ".jpg";
			} else {
				d.src = context + "/images/myl/" + origen + ".jpg";
			}
		}else if(origen=="mano1"){
			var divMano=document.getElementById(origen.replace("1","2"));
			var n=divMano.childNodes.length-1;
			$("#card"+n).remove();
		}else if(origen.indexOf("2")!=-1){
			/**
			 * cambia la carta de propietario
			 */
			for(var c=0;c<obj[origen.replace("2","1")].length;c++){
				if(obj[origen.replace("2","1")][c].idTemp==card.idTemp.substring(2)){
					obj[origen.replace("2","1")].splice(c,1);
					$("#"+card.idTemp.substring(2)).remove();
				}
			}
			
			if(origen=="cementerio2" || origen=="destierro2" || origen=="remocion2"){
				origen=origen.replace("2","1");
				var d = document.getElementById(origen);
				if (obj[origen].length != 0) {
					d.src = context + "/images/myl/"+obj[origen][0].siglas+"/" + obj[origen][0].numero+ ".jpg";
				} else {
					d.src = context + "/images/myl/" + origen + ".jpg";
				}
			}
		}
		
		
		objOp[destino].unshift(card);
		var img=createCardOp(0,context,destino);
		var divDest=document.getElementById(destino.replace("1","2"));
		divDest.appendChild(img);
		
	}else if(destino=="cementerio1" || destino=="destierro1" || destino=="remocion1"){
				
		if(origen!="mano1" && origen!="deck1" && origen!="cementerio1" && origen!="destierro1" && origen!="remocion1"){
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){
					objOp[origen].splice(c,1);					
				}
			}
			$("#"+card.idTemp).remove();
		}else if(origen=="cementerio1" || origen=="destierro1" || origen=="remocion1"){
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){					
					objOp[origen].splice(c,1);					
				}
			}
			var ori = document.getElementById(origen.replace("1","2"));
			if (objOp[origen].length != 0) {
				ori.src = context + "/images/myl/"+objOp[origen][0].siglas+"/" + objOp[origen][0].numero+ ".jpg";
			} else {
				ori.src = context + "/images/myl/" + origen + ".jpg";
			}
		}else if(origen=="mano1"){
			var divMano=document.getElementById(origen.replace("1","2"));
			var n=divMano.childNodes.length-1;
			$("#card"+n).remove();
		}
		
		objOp[destino].unshift(card);
		var dest = document.getElementById(destino.replace("1","2"));
		dest.src = context + "/images/myl/"+objOp[destino][0].siglas+"/"+ objOp[destino][0].numero + ".jpg";
		
		
	}else if(destino=="mano1"){
		if(origen!="mano1" && origen!="deck1" && origen!="cementerio1" && origen!="destierro1" && origen!="remocion1"){
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){
					objOp[origen].splice(c,1);					
				}
			}
			$("#"+card.idTemp).remove();
			var divMano=document.getElementById(destino.replace("1","2"));
			divMano.appendChild(createReverseCard(divMano.childNodes.length,context));
			
		}else if(origen=="cementerio1" || origen=="destierro1" || origen=="remocion1"){
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){					
					objOp[origen].splice(c,1);					
				}
			}
			var ori = document.getElementById(origen.replace("1","2"));
			if (objOp[origen].length != 0) {
				ori.src = context + "/images/myl/"+objOp[origen][0].siglas+"/" + objOp[origen][0].numero+ ".jpg";
			} else {
				ori.src = context + "/images/myl/" + origen + ".jpg";
			}
			var divMano=document.getElementById(destino.replace("1","2"));
			divMano.appendChild(createReverseCard(divMano.childNodes.length,context));
		}else if(origen=="deck1"){
			var divMano=document.getElementById(destino.replace("1","2"));
			divMano.appendChild(createReverseCard(divMano.childNodes.length,context));
		}
		
		
	}else if(destino=="deck1"){
		if(origen!="mano1" && origen!="deck1" && origen!="cementerio1" && origen!="destierro1" && origen!="remocion1"){
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){			
					objOp[origen].splice(c,1);					
				}
			}
			$("#"+card.idTemp).remove()
		}else if(origen=="cementerio1" || origen=="destierro1" || origen=="remocion1"){
			for(var c=0;c<objOp[origen].length;c++){
				if(objOp[origen][c].idTemp==card.idTemp){			
					objOp[origen].splice(c,1);					
				}
			}
			var ori = document.getElementById(origen.replace("1","2"));
			if (objOp[origen].length != 0) {
				ori.src = context + "/images/myl/"+objOp[origen][0].siglas+"/" + objOp[origen][0].numero+ ".jpg";
			} else {
				ori.src = context + "/images/myl/" + origen + ".jpg";
			}			
		}else if(origen=="mano1"){
			var divMano=document.getElementById(origen.replace("1","2"));
			var n=divMano.childNodes.length-1;
			$("#card"+n).remove();
		}				
	}
}