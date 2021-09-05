package com.UnayShah.Authenticator.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AttemptLogin")
public class AttemptLogin {
    @Id
    private String sessionId;
    private String websiteId;

    public AttemptLogin(String websiteId) {
        this.websiteId = websiteId;
        this.sessionId = UUID.randomUUID().toString();
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getWebsiteId() {
        return websiteId;
    }
}
