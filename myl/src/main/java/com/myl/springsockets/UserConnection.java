package com.myl.springsockets;

import org.springframework.web.socket.WebSocketSession;

public class UserConnection {

	private String userName;
	private String formatOrUser;
	private WebSocketSession session;

	public UserConnection(String userName, String formatOrUser,WebSocketSession session) {
		this.userName = userName;
		this.formatOrUser = formatOrUser;
		this.session = session;
	}

	public String getUserName() {
		return userName;
	}

	public String getFormatOrUser() {
		return formatOrUser;
	}

	public WebSocketSession getSession() {
		return session;
	}

}