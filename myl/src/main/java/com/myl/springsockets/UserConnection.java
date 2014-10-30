package com.myl.springsockets;

import java.io.Serializable;

import org.springframework.web.socket.WebSocketSession;

public class UserConnection implements Serializable{

	private final String userName;
	private final String formatOrUser;
//	private final WebSocketSession session;

	public UserConnection(String userName, String formatOrUser) {
		this.userName = userName;
		this.formatOrUser = formatOrUser;
//		this.session = session;
	}

	public String getUserName() {
		return userName;
	}

	public String getFormatOrUser() {
		return formatOrUser;
	}

//	public WebSocketSession getSession() {
//		return session;
//	}

}