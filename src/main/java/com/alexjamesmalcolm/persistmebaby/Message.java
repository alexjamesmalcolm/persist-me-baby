package com.alexjamesmalcolm.persistmebaby;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	long id;
	String text;

	public Message(String text) {
		this.text = text;
	}

	public void updateText(String newText) {
		text = newText;
	}
}
