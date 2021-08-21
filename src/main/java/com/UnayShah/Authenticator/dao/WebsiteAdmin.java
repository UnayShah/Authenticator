package com.UnayShah.Authenticator.dao;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "WebsiteAdmin")
public class WebsiteAdmin {
	@Id
	String username;
	String password;
	List<Website> listWebsites;
}
