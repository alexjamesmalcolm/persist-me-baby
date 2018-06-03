package com.alexjamesmalcolm.persistmebaby.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.Optional;

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
	public Object currentUser(Authentication auth) {
		String name = auth.getName();
		Optional<CustomUser> user = userRepo.findByGoogleName(name);
		if(user.isPresent()) {
			return user.get();
		}
		return "Cannot find user by name: " + name;
	}
	
	@RequestMapping(value = "/name", method = GET)
	public String currentName(Principal principal) {
		return principal.getName();
	}
	
}
