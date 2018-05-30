package com.alexjamesmalcolm.persistmebaby.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	public Principal currentUsername(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return principal;
	}
}
