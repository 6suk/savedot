package com.mulcam.finalproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.CashService;
import com.mulcam.finalproject.service.UserService;
import com.mulcam.finalproject.util.ImageUpload;

@Controller
public class TestControllerYejin {
	

//	@Autowired
//	ModelMapper modelMapper;
//	
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	ImageUpload imageUpload;
//	
//	@Autowired
//	CashService cashService;
//	
//	@GetMapping("/login/{uid}")
//	public String loginTemp(@PathVariable String uid, HttpServletRequest req) {
//		HttpSession session = req.getSession();
//		User user = userService.findById(uid).get();
//		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//		session.setAttribute("user", userDTO);
//		return "redirect:/mypage/main";
//	}
//	
//	@GetMapping("/logout")
//	public String logoutTemp(HttpServletRequest req) {
//		HttpSession session = req.getSession();
//		session.invalidate();
//		return "redirect:/mypage/main";
//	}

	

	
}
