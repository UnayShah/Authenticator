package com.UnayShah.Authenticator.controller;

import com.UnayShah.Authenticator.model.UserActive;
import com.UnayShah.Authenticator.service.UserActiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserActiveController {

    @Autowired
    UserActiveService userActiveService;

    @PostMapping(value = "login")
    public ResponseEntity<UserActive> login(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password, @RequestParam(name = "websiteId") String websiteId) {
        return new ResponseEntity<>(userActiveService.login(username, password, websiteId), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "refreshSession")
    public ResponseEntity<UserActive> refreshSession(@RequestParam(name = "username") String username,
            @RequestParam(name = "sessionId") String sessionId) {
        return new ResponseEntity<>(userActiveService.refreshSession(username, sessionId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "logout")
    public ResponseEntity<Boolean> logout(@RequestParam(name = "username") String username,
            @RequestParam(name = "sessionId") String sessionId) {
        return new ResponseEntity<>(userActiveService.logout(username, sessionId), HttpStatus.ACCEPTED);
    }
}
