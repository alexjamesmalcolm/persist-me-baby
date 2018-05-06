package com.alexjamesmalcolm.persistmebaby;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {
	
	@Resource
	private MessageRepository messageRepo;

	@RequestMapping("/")
	public String displayHome(Model model) {
		Iterable<Message> messages = messageRepo.findAll();
		model.addAttribute("messages", messages);
		return "index";
	}
}
