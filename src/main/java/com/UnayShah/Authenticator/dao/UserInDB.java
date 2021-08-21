package com.UnayShah.Authenticator.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "UserInDB")
public class UserInDB {
	@Id
	String username;
	String password;
	String websiteId;

	public UserInDB(String username, String password, String websiteId) {
		super();
		this.username = username;
		this.password = password;
		this.websiteId = websiteId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setWebsiteId(String websiteId) {
		this.websiteId = websiteId;
	}

	@Override
	public String toString() {
		return "UserInDB [username=" + username + ", password=" + password + ", websiteId=" + websiteId + "]";
	}

}
