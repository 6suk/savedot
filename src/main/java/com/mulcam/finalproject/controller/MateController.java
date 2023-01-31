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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dto.LocationDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.MateService;
import com.mulcam.finalproject.service.ReverseGeocodeUtil;
import com.mulcam.finalproject.service.UserService;

@Controller
//@RequestMapping("/mate")
public class MateController {

	@Autowired
	MateService mateService;

	@Autowired
	UserService userService;
	
	@Autowired
	ReverseGeocodeUtil reverseGeocodeUtil;

	@GetMapping("/mate")
	public String writeGet() {
//		User user = new User();
//		user.setId("ko");
//		user.setPwd("ko");
//		userService.save(user);

//		User u = userService.findById("ko").get();
//		userService.delete(u);
		return "mate/write";
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

			System.out.println(mateDTO);
			mateService.save(mate, files);
		}
		return "mate/write";
	}
	
	@PostMapping("/write/location")	
	@ResponseBody
	public LocationDTO getLocation(LocationDTO param, Model model) {
		param = reverseGeocodeUtil.getArea(param);
		return param;
	}
	

	@GetMapping("/detail")
	public String detail(Model model) {
		Mate mate = mateService.findById("3").get();

		ModelMapper modelMapper = new ModelMapper();
		MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);

		System.out.println(mateDTO);
		model.addAttribute("mate", mateDTO);

		return "test/viewtest";
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
