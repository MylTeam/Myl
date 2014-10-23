
package com.myl.springsockets;

import java.io.IOException;
import java.util.ArrayList;
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


@Service
public class ChatService {
  
  private Set<WebSocketSession> conns = java.util.Collections.synchronizedSet(new HashSet<WebSocketSession>());
  private Map<WebSocketSession, ChatConnection> nickNames = new ConcurrentHashMap<WebSocketSession, ChatConnection>();
  private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
  private Gson jsonProcessor;

  public void registerOpenConnection(WebSocketSession session) {
    conns.add(session);
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
    if (!nickNames.containsKey(session) && message.contains("sessionInfo")) {
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
      
      sendConnectionInfo(session, sessionMessage.getSessionInfo().getUser());
      
      //Register the nickname with the 
      nickNames.put(session, new ChatConnection(sessionMessage.getSessionInfo().getUser(), sessionMessage.getSessionInfo().getFormatOrUserTwo(), session));
      
      //Enviar status a todos
      
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
  
  private void sendConnectionInfo(WebSocketSession session,String userName) {
      final List<String> activeUsers = getActiveUsers();
      final List<String> formats = getFormats();
      final ConnectionInfoMessage connectionInfoMessage = new ConnectionInfoMessage(userName,activeUsers,formats);
      try {
    	  session.sendMessage(new TextMessage(jsonProcessor.toJson(connectionInfoMessage)));          
      } catch (IOException e) {
      	LOGGER.error("No se pudo enviar el mensaje", e);
      }
  }
  
//  private void sendStatusInfoToOtherUsers(StatusInfoMessage message) {
//  	LOGGER.info("Enviado estado de: "+this.userName+" a los demás usuarios.");
//  	final Collection<ChatConnection> otherUsersConnections = getAllChatConnectionsExceptThis();            
//      for (ChatConnection connection : otherUsersConnections) {
//          try {                	
//              connection.getWsOutbound().writeTextMessage(CharBuffer.wrap(jsonProcessor.toJson(message)));
//          } catch (IOException e) {
//          	LOGGER.error("No se pudo enviar el mensaje de estado de la conexión", e);
//          }
//      }            
//  }
//  
//  private Collection<ChatConnection> getAllChatConnectionsExceptThis() {
//      final Collection<ChatConnection> allConnections = nickNames.values();
//      allConnections.remove(this);
//      return allConnections;
//  }
  
  private List<String> getActiveUsers() {
  	LOGGER.info("Obteniendo lista de usuarios, Total: "+nickNames.size());
      final List<String> activeUsers = new ArrayList<String>();
      for (ChatConnection connection : nickNames.values()) {            	
          activeUsers.add(connection.getUserName());
      }
      if(activeUsers.isEmpty()){
      	LOGGER.error("No hay usuarios activos");
      }
      return activeUsers;
  }
  
  private List<String> getFormats() {
      final List<String> formats = new ArrayList<String>();
      for (ChatConnection connection : nickNames.values()) {
      	formats.add(connection.getFormatOrUser());
      }
      if(formats.isEmpty()){
      	LOGGER.error("No hay usuarios activos");
      }
      return formats;
  }

private static class ChatConnection{
	  
      private final String userName;
      private final String formatOrUser;
      private final WebSocketSession session;
      
      private ChatConnection(String userName,String formatOrUser,WebSocketSession session){
    	  this.userName = userName;
          this.formatOrUser = formatOrUser;
          this.session=session;
      }
      
      public String getUserName() {
          return userName;
      }
      public String getFormatOrUser() {
          return formatOrUser;
      }
      public WebSocketSession getSession(){
    	  return session;
      }
      
  }
}
