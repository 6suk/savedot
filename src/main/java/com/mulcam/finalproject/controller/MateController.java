package com.mulcam.finalproject.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mulcam.finalproject.dto.ApplyDTO;
import com.mulcam.finalproject.dto.LocationDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.UserDTO;
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

	@GetMapping("/mate/test")
	public String writetest() {
//		User user = new User();
//		user.setId("ko");
//		user.setPwd("ko");
//		user.setNickname("프로 알뜰러");
//		user.setTel("01012345678");
//		User user2 = new User();
//		user2.setId("admin");
//		user2.setPwd("admin");
//		user2.setNickname("관리자");
//		user2.setTel("01012345689");
//		userService.save(user2);
//		userService.save(user);
		
//		User u = userService.findById("ko").get();
//		userService.delete(u);
		return "test/write";
	}
	
	@GetMapping("/mate")
	public String writeGet(Model model) {
//		User user = new User();
//		user.setId("ko");
//		user.setPwd("ko");
//		userService.save(user);

//		User u = userService.findById("ko").get();
//		userService.delete(u);
		User user = userService.findById("ko").get(); // 추후 세션에서 가져오기
		// mapper
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		model.addAttribute("user", userDTO); 
		return "mate/write";
	}

	@PostMapping("/mate")
	public String writePost(MateDTO mateDTO, String uid) {
		Optional<User> user_ = userService.findById(uid);
			User user = user_.get();
			mateDTO.setUser(user);
			List<MultipartFile> files = mateDTO.getReqimgs();

			// mapper
			ModelMapper modelMapper = new ModelMapper();
			Mate mate = modelMapper.map(mateDTO, Mate.class);
			Long mid = mateService.save(mate, files);
			System.out.println(mate);
			System.out.println(mateDTO);
			
			return "redirect:/detail/" + mid;
			
//			System.out.println(mate);
//			return null;
	}

	@PostMapping("/location")
	@ResponseBody
	public LocationDTO getLocation(LocationDTO param) {
		param = reverseGeocodeUtil.getArea(param);
		return param;
	}

	@GetMapping("/detail/{mid}")
	public String detail(@PathVariable Long mid, Model model) {
		// 로그인 임시
		User user = userService.findById("admin").get();
		Mate mate = mateService.findById(mid).get();

		ModelMapper modelMapper = new ModelMapper();
		MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		model.addAttribute("mate", mateDTO);
		model.addAttribute("user", userDTO); // 로그인 유저
		return "mate/detail";
	}

	@RequestMapping("/apply/{mid}")
	public String applyGet(@PathVariable Long mid, ApplyDTO applyDTO) {
		System.out.println(applyDTO);
		return null;
	}

//	@PostMapping("/apply")
//	public String applyPost() {
//		return null;
//	}

}
