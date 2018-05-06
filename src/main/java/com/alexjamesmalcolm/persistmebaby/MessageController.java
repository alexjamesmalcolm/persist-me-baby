package com.alexjamesmalcolm.persistmebaby;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

	@RequestMapping("/")
	public String displayHome() {
		return "index";
	}
}
