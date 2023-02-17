package com.mulcam.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.finalproject.dto.ImageDTO;
import com.mulcam.finalproject.util.ImageUpload;


@Controller
@RequestMapping("")
public class HomeController {
	@Autowired
	ImageUpload imageUpload;
	
	@GetMapping("")
	public String main() {
		return "redirect:/mypage/main";
	}
	
	@GetMapping("/savedot/upload/{path}/{downloadName}")
	public ResponseEntity<Resource> download(@ModelAttribute ImageDTO imgDTO)  {
		return imageUpload.download(imgDTO);
	}

}
