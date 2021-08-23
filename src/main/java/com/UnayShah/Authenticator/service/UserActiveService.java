package com.UnayShah.Authenticator.service;

import java.util.UUID;

import com.UnayShah.Authenticator.dao.UserActive;
import com.UnayShah.Authenticator.repository.UserActiveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActiveService {

    @Autowired
    UserActiveRepository userActiveRepository;
    @Autowired
    UserInDBService userInDBService;

    public UserActive login(String username, String password, String websiteId) {
        if (userInDBService.registeredUser(username, password, websiteId))
            return userActiveRepository.save(new UserActive(UUID.randomUUID().toString(), username));
        return null;
    }

    public UserActive refreshSession(String sessionId, String username) {
        if (userActiveRepository.findByAllParameters(sessionId, username).isPresent())
            return userActiveRepository.findByAllParameters(sessionId, username).get();
        return null;
    }

    public Boolean logout(String sessionId, String username) {
        if (userActiveRepository.findByAllParameters(sessionId, username).isPresent()) {
            userActiveRepository.delete(userActiveRepository.findByAllParameters(sessionId, username).get());
            return true;
        }
        return false;
    }
}
