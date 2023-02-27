package com.mulcam.finalproject.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.finalproject.dto.ImageDTO;
import com.mulcam.finalproject.util.ImageUploadUtil;

@Controller
@RequestMapping("")
public class HomeController {
	@Autowired
	ImageUploadUtil imageUpload;
	
	@Value("${spring.servlet.multipart.location}")
	private String location;

	@GetMapping("")
	public String main() {
		return "redirect:/mypage/main";
	}

	@GetMapping("/savedot/upload/{path}/{downloadName}")
	public ResponseEntity<Resource> download(@ModelAttribute ImageDTO imgDTO)  {
		Path path = Paths.get(location + File.separator + imgDTO.getPath() + File.separator + imgDTO.getDownloadName());

		try {
			String contentType = Files.probeContentType(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment")
					.filename(imgDTO.getDownloadName(), StandardCharsets.UTF_8).build());
			headers.add(HttpHeaders.CONTENT_TYPE, contentType);
			Resource resource = new InputStreamResource(Files.newInputStream(path));
			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
