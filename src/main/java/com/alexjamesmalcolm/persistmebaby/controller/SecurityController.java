package com.alexjamesmalcolm.persistmebaby.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	public Authentication currentUsername(Authentication auth) {
		return auth;
	}
}
