package com.UnayShah.Authenticator.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserActive")
public class UserActive {
	@Id
	String username;
	String sessionId;

	public UserActive(String username) {
		this.sessionId = UUID.randomUUID().toString();
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}
}