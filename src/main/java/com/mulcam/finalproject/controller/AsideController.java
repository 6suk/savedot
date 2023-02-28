package com.mulcam.finalproject.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mulcam.finalproject.entity.Profile;
import com.mulcam.finalproject.service.ProfileService;
import com.mulcam.finalproject.util.ImageUtil;

@Controller
@RequestMapping("/aside")
public class AsideController {

	@Autowired private ImageUtil imageUtil;
	@Autowired private ProfileService profileService;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	
	@ResponseBody
	@GetMapping("/profile/{id}")
	public String profileForm(@PathVariable String id) {
		Profile profile = profileService.getProfileById(id);
		JSONObject obj = profileService.makeJsonProfile(profile);
		return obj.toString();
	}
	
	@ResponseBody
	@PostMapping("/profile")
	public String profile(MultipartHttpServletRequest req, HttpSession session, Model model) throws Exception {
		String id = req.getParameter("id");
		String filename = req.getParameter("filename");
		
		byte[] bytes = null;
		int size = 0;
		boolean hasImage = false;		// 이미지를 변경하지 않는 경우 대처
		MultipartFile image = req.getFile("image");
		if (image != null) {
			hasImage = true;
			bytes = image.getBytes();
			filename = image.getOriginalFilename();
			size = bytes.length;
		}
		Profile profile = new Profile(id, bytes, size, filename);
		System.out.println(profile);
		if (profileService.getProfileById(id) == null)
			profileService.insert(profile, session);
		else if (hasImage)
			profileService.update(profile, session);	// 이미지를 변경하는 경우
		return "0";
	}
	
	// BLOB 타입의 이미지를 출력하는 방법
	@GetMapping("/blob/{id}")
	public void blob(@PathVariable String id, HttpServletResponse res) throws Exception {
		Profile profile = profileService.getProfileById(id);
		int idx = profile.getFilename().lastIndexOf('.');
        String format = profile.getFilename().substring(idx + 1);
		
        byte[] bytes = imageUtil.squareImage(profile.getImage(), format);
		InputStream is = new ByteArrayInputStream(bytes);
		
		File file = new File(profile.getFilename());
	    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//	    System.out.println("MIME type: " + mimeType);
		res.setContentType(mimeType);
		IOUtils.copy(is, res.getOutputStream());
	}
	
}