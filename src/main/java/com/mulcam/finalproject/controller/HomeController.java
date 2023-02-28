package com.mulcam.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.mulcam.finalproject.dto.HomeDTO;
import com.mulcam.finalproject.service.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	@GetMapping("")
	public String main() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String HomeGet(Model model) {
		HomeDTO homeDTO = homeService.getHomeDTO();
		model.addAttribute("home", homeDTO);
		return "home/home";
	}

}
