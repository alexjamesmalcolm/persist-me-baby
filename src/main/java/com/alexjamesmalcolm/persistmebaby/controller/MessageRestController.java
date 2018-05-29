package com.alexjamesmalcolm.persistmebaby.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexjamesmalcolm.persistmebaby.message.Message;
import com.alexjamesmalcolm.persistmebaby.message.MessageRepository;

@RestController
public class MessageRestController {

	@Resource
	private MessageRepository messageRepo;

	@RequestMapping(path = "/messages", method = POST)
	private Message receivePostRequestOnMessages(@RequestParam String text, Principal principal) {
		System.out.println(principal);
		Message message = new Message(text, principal);
		message = messageRepo.save(message);
		return message;
	}

	@RequestMapping(path = "/messages/{messageId}", method = GET)
	private Message receiveGetRequestOnAMessage(@PathVariable long messageId) {
		Message message = messageRepo.findOne(messageId);
		return message;
	}

	@RequestMapping(path = "/messages", method = GET)
	private Iterable<Message> receiveGetRequestOnMessages() {
		Iterable<Message> messages = messageRepo.findAll();
		return messages;
	}
	
	@RequestMapping(path = "/messages/{messageId}", method = PUT)
	private Message receivePutRequestOnAMessage(@PathVariable long messageId, @RequestParam String text) {
		Message message = messageRepo.findOne(messageId);
		message.updateText(text);
		message = messageRepo.save(message);
		return message;
	}
	
	@RequestMapping(path = "/messages/{messageId}", method = DELETE)
	private void receiveDeleteRequestOnAMessage(@PathVariable long messageId) {
		messageRepo.delete(messageId);
	}
}
