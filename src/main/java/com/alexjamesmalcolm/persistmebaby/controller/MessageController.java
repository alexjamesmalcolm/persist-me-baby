package com.alexjamesmalcolm.persistmebaby.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexjamesmalcolm.persistmebaby.message.Message;
import com.alexjamesmalcolm.persistmebaby.message.MessageRepository;

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
