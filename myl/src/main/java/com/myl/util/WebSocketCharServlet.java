package com.myl.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.myl.messages.CardInfoMessage;
import com.myl.messages.CardListInfoMessage;
import com.myl.messages.ConnectionInfoMessage;
import com.myl.messages.MessageInfoMessage;
import com.myl.messages.PhaseInfoMessage;
import com.myl.messages.StatusInfoMessage;
import com.myl.messages.TargetInfoMessage;

//@WebServlet(urlPatterns = "/chat")
public class WebSocketCharServlet extends WebSocketServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketCharServlet.class);

    private static final Map<String, ChatConnection> CONNECTIONS = new HashMap<String, ChatConnection>();       
    
    @Override
    protected boolean verifyOrigin(String origin) {
        return true;
    }

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
        final String connectionId = request.getSession().getId();
        final String userName = request.getParameter("userName");
        return new ChatConnection(connectionId, userName);
    }

    private static class ChatConnection extends MessageInbound {

        private final String connectionId;

        private final String userName;

        private final Gson jsonProcessor;

        private ChatConnection(String connectionId, String userName) {
            this.connectionId = connectionId;
            this.userName = userName;
            this.jsonProcessor = new Gson();
        }

        @Override
        protected void onOpen(WsOutbound outbound) {
            sendConnectionInfo(outbound);
            sendStatusInfoToOtherUsers(new StatusInfoMessage(userName, StatusInfoMessage.STATUS.CONNECTED));
            CONNECTIONS.put(connectionId, this);
        }

        @Override
        protected void onClose(int status) {
            sendStatusInfoToOtherUsers(new StatusInfoMessage(userName, StatusInfoMessage.STATUS.DISCONNECTED));
            CONNECTIONS.remove(connectionId);
        }

        @Override
        protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {
            throw new UnsupportedOperationException("Binary messages not supported");
        }

        @Override
        protected void onTextMessage(CharBuffer charBuffer) throws IOException {
        	System.out.println(charBuffer.toString());
        	if(charBuffer.toString().contains("messageInfo")){
        		final MessageInfoMessage message = jsonProcessor.fromJson(charBuffer.toString(), MessageInfoMessage.class);
        		final ChatConnection destinationConnection = getDestinationUserConnection(message.getMessageInfo().getTo());
                if (destinationConnection != null) {
                    final CharBuffer jsonMessage = CharBuffer.wrap(jsonProcessor.toJson(message));
                    destinationConnection.getWsOutbound().writeTextMessage(jsonMessage);
                } else {
                	System.out.println("Se está intentando enviar un mensaje a un usuario no conectado");
                	LOGGER.warn("Se está intentando enviar un mensaje a un usuario no conectado");
                }
        	}else if(charBuffer.toString().contains("cardInfo")){
        		final CardInfoMessage message = jsonProcessor.fromJson(charBuffer.toString(), CardInfoMessage.class);
        		final ChatConnection destinationConnection = getDestinationUserConnection(message.getCardInfo().getTo());
                if (destinationConnection != null) {
                    final CharBuffer jsonMessage = CharBuffer.wrap(jsonProcessor.toJson(message));
                    destinationConnection.getWsOutbound().writeTextMessage(jsonMessage);
                } else {
                	System.out.println("Se está intentando enviar un mensaje a un usuario no conectado");
                }
        	}else if(charBuffer.toString().contains("cardListInfo")){
        		final CardListInfoMessage message = jsonProcessor.fromJson(charBuffer.toString(), CardListInfoMessage.class);
        		final ChatConnection destinationConnection = getDestinationUserConnection(message.getCardListInfo().getTo());
                if (destinationConnection != null) {
                    final CharBuffer jsonMessage = CharBuffer.wrap(jsonProcessor.toJson(message));
                    destinationConnection.getWsOutbound().writeTextMessage(jsonMessage);
                } else {
                	System.out.println("Se está intentando enviar un mensaje a un usuario no conectado");
                }
        	}else if(charBuffer.toString().contains("targetInfo")){
        		final TargetInfoMessage message = jsonProcessor.fromJson(charBuffer.toString(), TargetInfoMessage.class);
        		final ChatConnection destinationConnection = getDestinationUserConnection(message.getTargetInfo().getTo());
                if (destinationConnection != null) {
                    final CharBuffer jsonMessage = CharBuffer.wrap(jsonProcessor.toJson(message));
                    destinationConnection.getWsOutbound().writeTextMessage(jsonMessage);
                } else {
                	System.out.println("Se está intentando enviar un mensaje a un usuario no conectado");
                }
        	}else if(charBuffer.toString().contains("phaseInfo")){
        		final PhaseInfoMessage message = jsonProcessor.fromJson(charBuffer.toString(), PhaseInfoMessage.class);
        		final ChatConnection destinationConnection = getDestinationUserConnection(message.getPhaseInfo().getTo());
                if (destinationConnection != null) {
                    final CharBuffer jsonMessage = CharBuffer.wrap(jsonProcessor.toJson(message));
                    destinationConnection.getWsOutbound().writeTextMessage(jsonMessage);
                } else {
                	System.out.println("Se está intentando enviar un mensaje a un usuario no conectado");
                }
        	}
            
                        
            
        }

        public String getUserName() {
            return userName;
        }

        private void sendConnectionInfo(WsOutbound outbound) {
            final List<String> activeUsers = getActiveUsers();
            final ConnectionInfoMessage connectionInfoMessage = new ConnectionInfoMessage(userName, activeUsers);
            try {
                outbound.writeTextMessage(CharBuffer.wrap(jsonProcessor.toJson(connectionInfoMessage)));
            } catch (IOException e) {
            	System.out.println("No se pudo enviar el mensaje "+ e);
            	LOGGER.error("No se pudo enviar el mensaje", e);
            }
        }

        private List<String> getActiveUsers() {
            final List<String> activeUsers = new ArrayList<String>();
            for (ChatConnection connection : CONNECTIONS.values()) {
                activeUsers.add(connection.getUserName());
            }
            return activeUsers;
        }

        private void sendStatusInfoToOtherUsers(StatusInfoMessage message) {
            final Collection<ChatConnection> otherUsersConnections = getAllChatConnectionsExceptThis();
            for (ChatConnection connection : otherUsersConnections) {
                try {
                    connection.getWsOutbound().writeTextMessage(CharBuffer.wrap(jsonProcessor.toJson(message)));
                } catch (IOException e) {
                	System.out.println("No se pudo enviar el mensaje "+ e);
                	LOGGER.error("No se pudo enviar el mensaje", e);
                }
            }
        }

        private Collection<ChatConnection> getAllChatConnectionsExceptThis() {
            final Collection<ChatConnection> allConnections = CONNECTIONS.values();
            allConnections.remove(this);
            return allConnections;
        }

        private ChatConnection getDestinationUserConnection(String destinationUser) {
            for (ChatConnection connection : CONNECTIONS.values()) {
                if (destinationUser.equals(connection.getUserName())) {
                    return connection;
                }
            }
            return null;
        }

    }

}
