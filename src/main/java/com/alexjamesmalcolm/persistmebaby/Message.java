package com.alexjamesmalcolm.persistmebaby;

public class Message {

	String text;

	public Message(String text) {
		this.text = text;
	}

	public void updateText(String newText) {
		text = newText;
	}
}
