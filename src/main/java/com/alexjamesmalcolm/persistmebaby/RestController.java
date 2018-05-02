package com.alexjamesmalcolm.persistmebaby;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller
public class RestController {
	
	@Resource
	private MessageRepository messageRepo;
	
}
