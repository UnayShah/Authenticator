package com.UnayShah.Authenticator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping
	public ResponseEntity<String> hello() {
		return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);
	}
}
