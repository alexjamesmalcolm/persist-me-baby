package com.alexjamesmalcolm.persistmebaby.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	
	@RequestMapping(value = "/principal-string", method = GET)
	public String currentPrincipalString(Principal principal) {
		return principal.toString();
	}
	
	@RequestMapping(value = "/auth", method = GET)
	public Authentication currentAuth(Authentication auth) {
		return auth;
	}
	
	@RequestMapping(value = "/auth-string", method = GET)
	public String currentAuthString(Authentication auth) {
		return auth.toString();
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
	
	@RequestMapping(value = "/auth-details", method = GET)
	public Object currentAuthDetails(Authentication auth) {
		return auth.getDetails();
	}
	
	@RequestMapping(value = "/auth-principal", method = GET)
	public Object currentAuthPrinciple(Authentication auth) {
		return auth.getPrincipal();
	}
	
	@RequestMapping(value = "/auth-authorities", method = GET)
	public Collection<? extends GrantedAuthority> currentAuthorities(Authentication auth) {
		return auth.getAuthorities();
	}
	
	@RequestMapping(value = "/authentication-principal", method = GET)
	public CustomUser authenticationPrincipal(@AuthenticationPrincipal CustomUser user) {
		return user;
	}
	
}
