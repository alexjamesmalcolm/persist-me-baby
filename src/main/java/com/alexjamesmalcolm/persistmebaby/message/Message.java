package com.alexjamesmalcolm.persistmebaby.message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private long id;
	@Lob
	private String text;

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
