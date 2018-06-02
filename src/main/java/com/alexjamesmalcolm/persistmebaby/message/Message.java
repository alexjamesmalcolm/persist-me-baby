package com.alexjamesmalcolm.persistmebaby.message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.alexjamesmalcolm.persistmebaby.controller.CustomUser;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private long id;
	private String text;
	@ManyToOne
	private CustomUser user;

	public Message(String text, CustomUser user) {
		this.text = text;
		this.user = user;
	}
	
	public Message() {}

	public void updateText(String newText) {
		text = newText;
	}
	
	public String getText() {
		return text;
	}
}
