package com.UnayShah.Authenticator.service;

import com.UnayShah.Authenticator.model.AttemptLogin;
import com.UnayShah.Authenticator.repository.AttemptLoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptLoginService {
    @Autowired
    AttemptLoginRepository attemptLoginRepository;

    public String attemptLogin(String websiteId) {
        return attemptLoginRepository.save(new AttemptLogin(websiteId)).getSessionId();
    }

    public String getWebsiteId(String sessionId) {
        return attemptLoginRepository.findById(sessionId).isPresent()
                ? attemptLoginRepository.findById(sessionId).get().getWebsiteId()
                : null;
    }

    public Boolean removeAttemptLogin(String sessionId) {
        if (attemptLoginRepository.findById(sessionId).isPresent()) {
            attemptLoginRepository.deleteById(sessionId);
            return attemptLoginRepository.findById(sessionId).isEmpty();
        }
        return false;
    }
}
