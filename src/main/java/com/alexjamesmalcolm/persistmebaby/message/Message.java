package com.alexjamesmalcolm.persistmebaby.message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue
	long id;
	String text;

	public Message(String text) {
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
