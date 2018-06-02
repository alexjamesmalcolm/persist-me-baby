package com.alexjamesmalcolm.persistmebaby.customuser;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.alexjamesmalcolm.persistmebaby.message.Message;

@Entity
public class CustomUser {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(mappedBy = "user")
	private Collection<Message> messages;
	
	private String fullName;
	private String googleName;
	
	public CustomUser(String fullName) {
		this.fullName = fullName;
	}
	
	@SuppressWarnings("unused")
	private CustomUser() {}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setGoogleName(String googleName) {
		this.googleName = googleName;
	}
	
	public String getGoogleName() {
		return googleName;
	}
	
	public Collection<Message> getMessages() {
		return messages;
	}
	
}
