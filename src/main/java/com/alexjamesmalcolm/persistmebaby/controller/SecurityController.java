package com.alexjamesmalcolm.persistmebaby.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alexjamesmalcolm.persistmebaby.CustomUser;

@RestController
public class SecurityController {
	
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public Authentication currentAuth(Authentication auth) {
		return auth;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public CustomUser currentUser(@AuthenticationPrincipal CustomUser user) {
		return user;
	}
}
