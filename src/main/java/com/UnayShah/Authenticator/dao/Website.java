package com.UnayShah.Authenticator.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "Website")
public class Website {
	@Id
	String websiteId;
	String websiteName;
	String websiteRedirectURL;
}
