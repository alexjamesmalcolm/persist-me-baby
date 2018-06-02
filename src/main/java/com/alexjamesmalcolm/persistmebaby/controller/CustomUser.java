package com.alexjamesmalcolm.persistmebaby.controller;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.alexjamesmalcolm.persistmebaby.message.Message;

@SuppressWarnings("serial")
public class CustomUser extends User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(mappedBy = "user")
	private Collection<Message> messages;

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public Collection<Message> getMessages() {
		return messages;
	}

}
