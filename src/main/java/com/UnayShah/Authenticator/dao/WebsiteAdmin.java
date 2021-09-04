package com.UnayShah.Authenticator.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WebsiteAdmin")
public class WebsiteAdmin {
	@Id
	String username;
	Set<Website> listWebsites;

	public WebsiteAdmin(String username) {
		this.username = username;
		this.listWebsites = new HashSet<>();
	}

	public String getUsername() {
		return username;
	}

	public Set<Website> getListWebsites() {
		return listWebsites;
	}

	public Boolean addWebsite(Website website) {
		return this.listWebsites.add(website);
	}

	public Boolean removeWebsite(Website website) {
		return this.listWebsites.remove(website);
	}
}
