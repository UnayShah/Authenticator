package com.UnayShah.Authenticator.controller;

import com.UnayShah.Authenticator.service.AttemptLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AttemptLoginController {
    @Autowired
    AttemptLoginService attemptLoginService;

    @GetMapping("attemptLogin")
    public ResponseEntity<String> attemptLogin(@RequestParam(name = "websiteId", required = true) String websiteId) {
        return new ResponseEntity<>(attemptLoginService.attemptLogin(websiteId), HttpStatus.ACCEPTED);
    }

    @GetMapping("getWebsiteId")
    public ResponseEntity<String> getWebsiteId(@RequestParam(name = "sessionId", required = true) String sessionId) {
        return new ResponseEntity<>(attemptLoginService.getWebsiteId(sessionId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("removeLoginAttempt")
    public ResponseEntity<Boolean> removeLoginAttempt(
            @RequestParam(name = "sessionId", required = true) String sessionId) {
        return new ResponseEntity<>(attemptLoginService.removeAttemptLogin(sessionId), HttpStatus.ACCEPTED);
    }
}
