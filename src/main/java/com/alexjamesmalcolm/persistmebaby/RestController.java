package com.alexjamesmalcolm.persistmebaby;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestController {

	@Resource
	private MessageRepository messageRepo;

	@RequestMapping(path = "/messages", method = POST)
	private Message receivePostRequestOnMessages(@RequestParam String text) {
		Message message = new Message(text);
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
