package com.UnayShah.Authenticator.service;

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

    /**
     * handle user login
     * 
     * @param username
     * @param password
     * @param websiteId
     * @return
     */
    public UserActive login(String username, String password, String websiteId) {
        if (userInDBService.registeredUser(username, password, websiteId))
            return userActiveRepository.save(new UserActive(username));
        return null;
    }

    public Boolean checkSession(String username, String sessionId) {
        if (refreshSession(username, sessionId) != null)
            return true;
        return false;
    }

    /**
     * refresh a user session by changing login time to be implemented in the future
     * 
     * @param sessionId
     * @param username
     * @return
     */
    public UserActive refreshSession(String username, String sessionId) {
        // TO DO: add timestamp field for logged in user and modify it to current time
        if (userActiveRepository.findByAllParameters(username, sessionId).isPresent())
            return userActiveRepository.findByAllParameters(username, sessionId).get();
        return null;
    }

    /**
     * logout user and remove session id
     * 
     * @param sessionId
     * @param username
     * @return
     */
    public Boolean logout(String username, String sessionId) {
        if (userActiveRepository.findByAllParameters(username, sessionId).isPresent()) {
            userActiveRepository.delete(userActiveRepository.findByAllParameters(username, sessionId).get());
            return true;
        }
        return false;
    }
}
