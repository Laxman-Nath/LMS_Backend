package com.lms.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@GetMapping("/login")
	public String Login() {
		return "You have logged in  successfully!";
	}
}
