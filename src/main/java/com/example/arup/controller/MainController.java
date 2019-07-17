package com.example.arup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model){
		return "login";
	}
	@GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
