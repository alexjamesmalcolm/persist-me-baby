package com.alexjamesmalcolm.persistmebaby;

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
	
	private CustomUser() {}
	
	public Collection<Message> getMessages() {
		return messages;
	}

}
