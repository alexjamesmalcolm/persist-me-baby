package com.alexjamesmalcolm.persistmebaby.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexjamesmalcolm.persistmebaby.customuser.CustomUser;
import com.alexjamesmalcolm.persistmebaby.customuser.CustomUserRepository;
import com.alexjamesmalcolm.persistmebaby.message.Message;
import com.alexjamesmalcolm.persistmebaby.message.MessageRepository;

@RestController
public class MessageRestController {

	@Resource
	private MessageRepository messageRepo;
	
	@Resource
	private CustomUserRepository userRepo;
	
	@Resource
	private EntityManager entityManager;

	@RequestMapping(path = "/messages", method = POST)
	@Transactional
	public Message receivePostRequestOnMessages(@RequestParam String text, Authentication auth) {
		String googleName = auth.getName();
		Optional<CustomUser> optionalUser = userRepo.findByGoogleName(googleName);
		if(optionalUser.isPresent()) {
			Message message = new Message(text, optionalUser.get());
			message = messageRepo.save(message);
			return message;
		} else {
			CustomUser user = new CustomUser(googleName);
			user.setGoogleName(googleName);
			userRepo.save(user);
			entityManager.flush();
			entityManager.clear();
			optionalUser = userRepo.findByGoogleName(googleName);
			user = optionalUser.get();
			Message message = new Message(text, user);
			message = messageRepo.save(message);
			return message;
		}
	}

	@RequestMapping(path = "/messages/{messageId}", method = GET)
	public Message receiveGetRequestOnAMessage(@PathVariable long messageId) {
		Optional<Message> optionalMessage = messageRepo.findById(messageId);
		if(optionalMessage.isPresent()) {
			Message message = optionalMessage.get();
			return message;
		} else {
			return null;
		}
	}

	@RequestMapping(path = "/messages", method = GET)
	public Iterable<Message> receiveGetRequestOnMessages() {
		Iterable<Message> messages = messageRepo.findAll();
		return messages;
	}
	
	@RequestMapping(path = "/messages/{messageId}", method = PUT)
	public Message receivePutRequestOnAMessage(@PathVariable long messageId, @RequestParam String text) {
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
	public void receiveDeleteRequestOnAMessage(@PathVariable long messageId) {
		messageRepo.deleteById(messageId);
	}
	
	@RequestMapping(path = "/users", method = GET)
	public Iterable<CustomUser> receiveGetRequestOnUsers() {
		return userRepo.findAll();
	}
	
	@RequestMapping(path = "/users/{userId}", method = GET)
	public CustomUser receiveGetRequestOnAUser(@PathVariable long userId) {
		Optional<CustomUser> potentialUser = userRepo.findById(userId);
		if(potentialUser.isPresent()) {
			CustomUser user = potentialUser.get();
			return user;
		} else {
			return null;
		}
	}
}
