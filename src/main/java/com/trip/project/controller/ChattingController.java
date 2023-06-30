package com.trip.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chatting")
public class ChattingController {

	@RequestMapping
	public String chattingMain() {
		
		return "chattingRoom";
	}
}
