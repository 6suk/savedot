package com.mulcam.finalproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.service.CashListService;

@Controller
@RequestMapping("/pay")
public class PayCalcController {
	
	@Autowired
	private CashListService cashListService;
	
	@GetMapping("")
	public String PayGet() {
		return "home/pay";
	}
	
	@PostMapping("")
	@ResponseBody
	public UserDTO PayAjax(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		user.setSumNowExpense(cashListService.sumNowExpense(user.getId()));
		return user;
	}
	
}
