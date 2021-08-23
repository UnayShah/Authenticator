package com.UnayShah.Authenticator.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserActive")
public class UserActive {
	@Id
	String sessionId;
	String username;

	public UserActive(String sessionId, String username) {
		this.sessionId = sessionId;
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}
}