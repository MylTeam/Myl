
package com.myl.springsockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.myl.messages.ConnectionInfoMessage;
import com.myl.messages.SessionInfoMessage;
import com.myl.messages.StatusInfoMessage;


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
//    String nick = nickNames.get(session);
//    conns.remove(session);
//    nickNames.remove(session);
//    if (nick!= null) {
//      String messageToSend = "{\"removeUser\":\"" + nick + "\"}";
//      for (WebSocketSession sock : conns) {
//        try {
//          sock.sendMessage(new TextMessage(messageToSend));
//        } catch (IOException e) {
//          System.out.println("IO exception when sending remove user message");
//        }
//      }
//    }    
  }
  
  public void processMessage(WebSocketSession session, String message) {
	  LOGGER.info("En process message");	  
    if (!nickNames.containsKey(session) && message.contains("sessionInfo")) {
    	LOGGER.info("Registrando usuario");
      //No nickname has been assigned by now
      //the first message is the nickname
      //escape the " character first
//      message = message.replace("\"", "\\\"");
//      
//      //broadcast all the nicknames to him
//      for (String nick : nickNames.values()) {
//        try {
//          session.sendMessage(new TextMessage("{\"addUser\":\"" + nick + "\"}"));
//        } catch (IOException e) {
//          System.out.println("Error when sending addUser message");          
//        }
//      }
      
      //Recupera los datos de la sesion del usuario
      SessionInfoMessage sessionMessage = jsonProcessor.fromJson(message, SessionInfoMessage.class);
      LOGGER.info("msj sesion: "+sessionMessage );
    //Register the nickname with the
      UserConnection connection=new UserConnection(sessionMessage.getSessionInfo().getUser(), sessionMessage.getSessionInfo().getFormatOrUserTwo(), session);
      
      sendConnectionInfo(session,connection);
            
      nickNames.put(session,connection);
      
      //Enviar status a todos      
      sendStatusInfoToOtherUsers(new StatusInfoMessage(connection.getUserName(), connection.getFormatOrUser(), StatusInfoMessage.STATUS.CONNECTED),connection);

      
      //broadcast him to everyone now
//      String messageToSend = "{\"addUser\":\"" + message + "\"}";
//      for (WebSocketSession sock : conns) {
//        try {
//          sock.sendMessage(new TextMessage(messageToSend));
//        } catch (IOException e) {
//          System.out.println("Error when sending broadcast addUser message");
//        }
//      }
    
//    } else {
//      //Broadcast the message
//      String messageToSend = "{\"nickname\":\"" + nickNames.get(session)
//          + "\", \"message\":\"" + message.replace("\"", "\\\"") +"\"}";
//      for (WebSocketSession sock : conns) {
//        try {
//          sock.sendMessage(new TextMessage(messageToSend));
//        } catch (IOException e) {
//          System.out.println("Error when sending message message");
//        }
//      }
    }
  }
  
  private void sendConnectionInfo(WebSocketSession session,UserConnection connection) {
	  LOGGER.info("Enviando msj a: "+session.toString());
      final List<String> activeUsers = getActiveUsers();
      final List<String> formats = getFormats();
      final ConnectionInfoMessage connectionInfoMessage = new ConnectionInfoMessage(connection.getUserName(),activeUsers,formats);
      try {
    	  LOGGER.info(connectionInfoMessage+" , "+session);
    	  session.sendMessage(new TextMessage(jsonProcessor.toJson(connectionInfoMessage)));
    	  LOGGER.info("Msj enviado");
      } catch (IOException e) {
      	LOGGER.error("No se pudo enviar el mensaje", e);
      }
  }
  
  private void sendStatusInfoToOtherUsers(StatusInfoMessage message, UserConnection connection) {
  	LOGGER.info("Enviado estado de: "+connection.getUserName()+" a los demás usuarios.");
  	final Collection<UserConnection> otherUsersConnections = getAllChatConnectionsExceptThis(connection.getUserName());
  	LOGGER.info("size: "+otherUsersConnections.size());
      for (UserConnection connectionAux : otherUsersConnections) {
    	  LOGGER.info(connectionAux.getUserName());
          try {
        	  connectionAux.getSession().sendMessage(new TextMessage(jsonProcessor.toJson(message)));        	                
          } catch (IOException e) {
          	LOGGER.error("No se pudo enviar el mensaje de estado de la conexión", e);
          }
      }            
  }
  
  private Collection<UserConnection> getAllChatConnectionsExceptThis(String userName) {
	  LOGGER.info("getallchatconnectionsExceptThis");
      final Collection<UserConnection> allConnections = nickNames.values();
      LOGGER.info("size all "+nickNames.size());
      for(UserConnection connection:allConnections){
    	  if(connection.getUserName().equals(userName)){
    		  allConnections.remove(connection);
    	  }
      }      
      return allConnections;
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
