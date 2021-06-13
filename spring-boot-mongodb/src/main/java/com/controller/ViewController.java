package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping({ "/", "home" })
	public String homePage(Model model) {
		return "home";
	}

}
