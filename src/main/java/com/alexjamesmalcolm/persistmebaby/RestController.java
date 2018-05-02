package com.alexjamesmalcolm.persistmebaby;

import javax.annotation.Resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestController {

	@Resource
	private CrudRepository<Message, Long> messageRepo;

	@RequestMapping(path = "/messages", method = RequestMethod.POST)
	private Message receiveAPostRequestOnMessages(@RequestParam String text) {
		Message message = new Message(text);
		message = messageRepo.save(message);
		return message;
	}

	@RequestMapping(path = "/messages/{messageId}", method = RequestMethod.GET)
	private Message receiveAGetRequestOnAMessage(@PathVariable long messageId) {
		Message message = messageRepo.findOne(messageId);
		return message;
	}

	@RequestMapping(path = "/messages", method = RequestMethod.GET)
	private Iterable<Message> receiveAGetRequestOnMessages() {
		Iterable<Message> messages = messageRepo.findAll();
		return messages;
	}
}
