package com.alexjamesmalcolm.persistmebaby.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	private Message receivePostRequestOnMessages(@RequestParam String text, @AuthenticationPrincipal CustomUser user) {
		Message message = new Message(text, user);
		message = messageRepo.save(message);
		return message;
	}

	@RequestMapping(path = "/messages/{messageId}", method = GET)
	private Message receiveGetRequestOnAMessage(@PathVariable long messageId) {
		Optional<Message> optionalMessage = messageRepo.findById(messageId);
		if(optionalMessage.isPresent()) {
			Message message = optionalMessage.get();
			return message;
		} else {
			return null;
		}
	}

	@RequestMapping(path = "/messages", method = GET)
	private Iterable<Message> receiveGetRequestOnMessages() {
		Iterable<Message> messages = messageRepo.findAll();
		return messages;
	}
	
	@RequestMapping(path = "/messages/{messageId}", method = PUT)
	private Message receivePutRequestOnAMessage(@PathVariable long messageId, @RequestParam String text) {
		Optional<Message> optionalMessage = messageRepo.findById(messageId);
		if(optionalMessage.isPresent()) {
			Message message = optionalMessage.get();
			message.updateText(text);
			messageRepo.save(message);
			return message;
		} else {
			return null;
		}
	}
	
	@RequestMapping(path = "/messages/{messageId}", method = DELETE)
	private void receiveDeleteRequestOnAMessage(@PathVariable long messageId) {
		messageRepo.deleteById(messageId);
	}
}
