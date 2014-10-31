
package com.myl.springsockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.myl.messages.ChatInfoMessage;
import com.myl.messages.ConnectionInfoMessage;
import com.myl.messages.MessageInfoMessage;
import com.myl.messages.SessionInfoMessage;
import com.myl.messages.StatusInfoMessage;
import com.myl.util.Util;



@Service
public class ChatService {
  
  private Set<WebSocketSession> conns = java.util.Collections.synchronizedSet(new HashSet<WebSocketSession>());  
  private Map<WebSocketSession, UserConnection> nickNames = java.util.Collections.synchronizedMap(new HashMap<WebSocketSession, UserConnection>());  
  private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
  private Gson jsonProcessor=new Gson();
  

  public void registerOpenConnection(WebSocketSession session) {
    conns.add(session);
    LOGGER.info("conexiones: "+conns.size());
  }
  
  public void registerCloseConnection(WebSocketSession session) {
    
    sendStatusInfoToOtherUsers(new StatusInfoMessage(nickNames.get(session).getUserName(), nickNames.get(session).getFormatOrUser(), StatusInfoMessage.STATUS.DISCONNECTED),session);    
    nickNames.remove(session);
    conns.remove(session);  
  }
  
  public void processMessage(WebSocketSession session, String message) {
	  LOGGER.info("En process message");	  
    if (!nickNames.containsKey(session) && message.contains("sessionInfo")) {
    	LOGGER.info("Registrando usuario");
  
      //Recupera los datos de la sesion del usuario
      SessionInfoMessage sessionMessage = jsonProcessor.fromJson(message, SessionInfoMessage.class);
      LOGGER.info("msj sesion: "+sessionMessage );
      final UserConnection userConnection=new UserConnection(sessionMessage.getSessionInfo().getUser(), sessionMessage.getSessionInfo().getFormatOrUserTwo());
      sendConnectionInfo(session,userConnection);
      nickNames.put(session,userConnection);
      sendStatusInfoToOtherUsers(new StatusInfoMessage(userConnection.getUserName(), userConnection.getFormatOrUser(), StatusInfoMessage.STATUS.CONNECTED),session);
           
    
    } else {
    	//mensajes enviados en el chat
    	if(message.contains("messageInfo")){
    		final MessageInfoMessage messageInfoMessage = jsonProcessor.fromJson(message, MessageInfoMessage.class);
    		sendMessage(messageInfoMessage.getMessageInfo().getTo(), messageInfoMessage);
       	}else if(message.contains("chatInfo")){
    		final ChatInfoMessage chatInfoMessage = jsonProcessor.fromJson(message, ChatInfoMessage.class);        		
    		sendMessageToAll(chatInfoMessage,session);	
    	}
    }
  }

  
  private void sendConnectionInfo(WebSocketSession session,UserConnection connection) {
	  LOGGER.info("Enviando msj a: "+session.toString());
      final List<String> activeUsers = getActiveUsers();
      final List<String> formats = getFormats();
      final ConnectionInfoMessage connectionInfoMessage = new ConnectionInfoMessage(connection.getUserName(),activeUsers,formats);
      try {
    	  LOGGER.info(jsonProcessor.toJson(connectionInfoMessage)+" , "+session);
    	  session.sendMessage(new TextMessage(jsonProcessor.toJson(connectionInfoMessage)));
    	  LOGGER.info("Msj enviado");
      } catch (IOException e) {
      	LOGGER.error("No se pudo enviar el mensaje", e);
      }
  }
  
  private void sendMessageToAll(Object message,WebSocketSession session) {
	  for(WebSocketSession sessionAux:conns){
		  if(!sessionAux.equals(session)){
				try {
					sessionAux.sendMessage(new TextMessage(jsonProcessor.toJson(message)));
				} catch (IOException e) {
					LOGGER.error("No se pudo enviar el mensaje de estado de la conexión", e);
				}
			}
	  }  	
  }
  
  private void sendMessage(String string,Object object) {
  	final WebSocketSession destinationConnection = getDestinationUserConnection(string);
      if (destinationConnection != null) {          
          try {
			destinationConnection.sendMessage(new TextMessage(jsonProcessor.toJson(object)));
		} catch (IOException e) {
			LOGGER.error("No se pudo enviar el mensaje", e);
		}
      } else {
      	LOGGER.warn("Se está intentando enviar un mensaje a un usuario no conectado");
      }
  }
  
  private WebSocketSession getDestinationUserConnection(String destinationUser) {
      for (UserConnection connection : nickNames.values()) {    	  
          if (destinationUser.equals(connection.getUserName())) {
        	  return Util.getKeyByValue(nickNames, connection);
          }
      }
      return null;
  }
  
  private void sendStatusInfoToOtherUsers(StatusInfoMessage message, WebSocketSession session) {
  	LOGGER.info("Enviado estado de: "+nickNames.get(session).getUserName()+" a los demás usuarios.");
  		for(WebSocketSession sessionAux:conns){
  			if(!sessionAux.equals(session)){
  				try {
					sessionAux.sendMessage(new TextMessage(jsonProcessor.toJson(message)));
				} catch (IOException e) {
					LOGGER.error("No se pudo enviar el mensaje de estado de la conexión", e);
				}
  			}
  		}
  }  
  
  private List<String> getActiveUsers() {
  	LOGGER.info("Obteniendo lista de usuarios, Total: "+nickNames.size());
      final List<String> activeUsers = new ArrayList<String>();
      for (UserConnection connection : nickNames.values()) {            	
          activeUsers.add(connection.getUserName());
      }
      if(activeUsers.isEmpty()){
      	LOGGER.error("No hay usuarios activos");
      }
      return activeUsers;
  }
  
  private List<String> getFormats() {
      final List<String> formats = new ArrayList<String>();
      for (UserConnection connection : nickNames.values()) {
      	formats.add(connection.getFormatOrUser());
      }
      if(formats.isEmpty()){
      	LOGGER.error("No hay usuarios activos");
      }
      return formats;
  }

}
