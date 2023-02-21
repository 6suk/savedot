package com.mulcam.finalproject.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
	
	@Value("${spring.servlet.multipart.location}")
	private String location;
	
	@GetMapping("")
	public String main() {
		return "redirect:/mypage/main";
	}
	
	@GetMapping("/savedot/upload/{path}/{downloadName}")
	public ResponseEntity<Resource> download(@ModelAttribute ImageDTO imgDTO)  {
		return imageUpload.download(imgDTO);
	}
	
	@GetMapping("/savedot/display/{path}/{downloadName}")
	public ResponseEntity<Resource> display(@ModelAttribute ImageDTO imgDTO)  {
		Resource resource = new FileSystemResource(location + File.separator + imgDTO.getPath() + File.separator + imgDTO.getDownloadName());
		if(!resource.exists()) 
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		HttpHeaders header = new HttpHeaders();
		Path filePath = null;
		try{
			filePath = Paths.get(location + File.separator + imgDTO.getPath() + File.separator + imgDTO.getDownloadName());
			header.add("Content-type", Files.probeContentType(filePath));
			header.add("Access-Control-Allow-Origin", "*");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	

}
