package com.sharad.tmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

	@RequestMapping("/hello")
	public ResponseEntity<String> hello()
	{
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}
	
}
