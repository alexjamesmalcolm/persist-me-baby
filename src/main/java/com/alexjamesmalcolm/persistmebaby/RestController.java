package com.alexjamesmalcolm.persistmebaby;

import javax.annotation.Resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestController {

	@Resource
	private CrudRepository<Message, Long> messageRepo;

	@RequestMapping(path = "/messages")
	private Message receiveAPostRequestOnMessages(@RequestParam String text) {
		Message message = new Message(text);
		message = messageRepo.save(message);
		return message;
	}
	
//	@RequestMapping(path = "/messages/{messageId}")

}
