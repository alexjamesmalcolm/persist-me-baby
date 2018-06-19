package com.alexjamesmalcolm.persistmebaby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login/oauth2")
	public String receiveRequestToLogin() {
		return "redirect:/oauth2/authorization/google";
	}

}
