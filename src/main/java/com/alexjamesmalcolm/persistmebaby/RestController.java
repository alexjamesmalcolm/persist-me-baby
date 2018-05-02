package com.alexjamesmalcolm.persistmebaby;

import javax.annotation.Resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

@Controller
public class RestController {
	
	@Resource
	private CrudRepository<Message, Long> messageRepo;
	
}
