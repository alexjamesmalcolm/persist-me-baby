package com.alexjamesmalcolm.persistmebaby.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexjamesmalcolm.persistmebaby.customuser.CustomUser;
import com.alexjamesmalcolm.persistmebaby.customuser.CustomUserRepository;

@RestController
public class SecurityController {
	
	@Resource
	private CustomUserRepository userRepo;
	
	@RequestMapping(value = "/principal", method = GET)
	public Principal currentPrincipal(Principal principal) {
		return principal;
	}
	
	@RequestMapping(value = "/auth", method = GET)
	public Authentication currentAuth(Authentication auth) {
		return auth;
	}
	
	@RequestMapping(value = "/user", method = GET)
	public CustomUser currentUser(Authentication auth) {
		String name = auth.getName();
		CustomUser user = userRepo.findByGoogleName(name);
		return user;
	}
	
}
