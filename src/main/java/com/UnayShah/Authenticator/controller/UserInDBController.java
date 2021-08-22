package com.UnayShah.Authenticator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.UnayShah.Authenticator.dao.UserInDB;
import com.UnayShah.Authenticator.service.UserInDBService;

@Controller
public class UserInDBController {
	@Autowired
	UserInDBService userInDBService;

	@PostMapping("newUser")
	public ResponseEntity<UserInDB> newUser(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "websiteId", required = true) String websiteId) {
		return new ResponseEntity<UserInDB>(userInDBService.newUser(username, password, websiteId),
				HttpStatus.ACCEPTED);
	}

	@PutMapping("editUser")
	public ResponseEntity<UserInDB> editUser(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "websiteId", required = true) String websiteId,
			@RequestParam(name = "newPassword", required = true) String newPassword,
			@RequestParam(name = "newWebsiteId", required = true) String newWebsiteId) {
		return new ResponseEntity<UserInDB>(
				userInDBService.editUser(username, password, websiteId, newPassword, newWebsiteId),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("removeUser")
	public ResponseEntity<Boolean> removeUser(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "websiteId", required = true) String websiteId) {
		return new ResponseEntity<>(userInDBService.removeUser(username, password, websiteId), HttpStatus.ACCEPTED);
	}
}
