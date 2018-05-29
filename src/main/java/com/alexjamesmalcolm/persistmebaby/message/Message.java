package com.alexjamesmalcolm.persistmebaby.message;

import java.security.Principal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private long id;
	private String text;

	public Message(String text, Principal principal) {
		this.text = text;
	}
	
	public Message() {}

	public void updateText(String newText) {
		text = newText;
	}
	
	public String getText() {
		return text;
	}
}
