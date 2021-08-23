package com.UnayShah.Authenticator.dao;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserInDB")
public class UserInDB {
	@Id
	String username;
	String password;
	Set<String> websiteId;

	public UserInDB(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		if (websiteId == null)
			this.websiteId = new TreeSet<>();
	}

	public UserInDB(String username, String password, String... websiteId) {
		this(username, password);
		this.websiteId.addAll(Arrays.asList(websiteId));
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

	public boolean addWebsiteId(String... websiteId) {
		return this.websiteId.addAll(Arrays.asList(websiteId));
	}

	public boolean containsWebsiteId(String websiteId) {
		return this.websiteId.contains(websiteId);
	}

	public boolean removeWebsiteId(String websiteId) {
		return this.websiteId.remove(websiteId);
	}
}
