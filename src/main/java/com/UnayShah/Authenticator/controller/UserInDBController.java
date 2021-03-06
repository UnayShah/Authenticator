package com.UnayShah.Authenticator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.UnayShah.Authenticator.model.UserInDB;
import com.UnayShah.Authenticator.service.UserInDBService;

@Controller
public class UserInDBController {
	@Autowired
	UserInDBService userInDBService;

	@PostMapping("newUser")
	public ResponseEntity<UserInDB> newUser(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password) {
		return new ResponseEntity<>(userInDBService.newUser(username, password), HttpStatus.ACCEPTED);
	}

	@PutMapping("editUser")
	public ResponseEntity<Boolean> editUser(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "newPassword", required = true) String newPassword, String sessionId) {
		return new ResponseEntity<>(userInDBService.editUser(username, password, newPassword, sessionId),
				HttpStatus.ACCEPTED);
	}

	@PutMapping("addWebsite")
	public ResponseEntity<Boolean> addWebsite(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "websiteId", required = true) String websiteId, String sessionId) {
		return new ResponseEntity<>(userInDBService.addWebsite(username, password, websiteId, sessionId),
				HttpStatus.ACCEPTED);
	}

	@PutMapping("removeWebsite")
	public ResponseEntity<Boolean> removeWebsite(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "websiteId", required = true) String websiteId, String sessionId) {
		return new ResponseEntity<>(userInDBService.removeWebsite(username, password, websiteId, sessionId),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("removeUser")
	public ResponseEntity<Boolean> removeUser(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "sessionId", required = true) String sessionId) {
		return new ResponseEntity<>(userInDBService.removeUser(username, password, sessionId), HttpStatus.ACCEPTED);
	}
}
