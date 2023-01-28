package com.mulcam.finalproject.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.MateService;
import com.mulcam.finalproject.service.UserService;

@Controller
//@RequestMapping("/mate")
public class MateController {

	@Autowired
	MateService mateService;

	@Autowired
	UserService userService;

	@GetMapping("/mate")
	public String writeGet() {
//		User user = new User();
//		user.setId("ko");
//		user.setPwd("ko");
//		userService.save(user);

//		User u = userService.findById("ko").get();
//		userService.delete(u);
		return "matetest";
	}

	@PostMapping("/mate")
	public String writePost(MateDTO mateDTO) {
		Optional<User> user_ = userService.findById("ko");
		if (user_.isPresent()) {
			User user = user_.get();
			mateDTO.setUser(user);

			List<MultipartFile> files = mateDTO.getReqimgs();

			// mapper
			ModelMapper modelMapper = new ModelMapper();
			Mate mate = modelMapper.map(mateDTO, Mate.class);

			mateService.save(mate, files);
		}
		return "matetest";
	}

	@GetMapping("/detail")
	public String detail(Model model) {
		Mate mate = mateService.findById("3").get();

		ModelMapper modelMapper = new ModelMapper();
		MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);

		System.out.println(mateDTO);
		model.addAttribute("mate", mateDTO);

		return "viewtest";
	}
	
	@RequestMapping("/apply/{mid}")
	public String applyGet(int mid) {
		return null;
	}
	
	@PostMapping("/apply")
	public String applyPost() {
		return null;
	}

}
