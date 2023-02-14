package com.mulcam.finalproject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mulcam.finalproject.dto.MateApplyDTO;
import com.mulcam.finalproject.dto.LocationDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateApply;
import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.MateApplyService;
import com.mulcam.finalproject.service.MateService;
import com.mulcam.finalproject.service.UserService;
import com.mulcam.finalproject.util.ReverseGeocodeUtil;

import io.netty.handler.codec.http.HttpRequest;

@Controller
@RequestMapping("/mate")
public class MateController {
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	MateService mateService;
	
	@Autowired
	MateApplyService applyService;

	@Autowired
	UserService userService;

	@Autowired
	ReverseGeocodeUtil reverseGeocodeUtil;
	
	
	/** Mate Write */
	@GetMapping("/write")
	public String writeGet(Model model) {
		User user = userService.findById("ko").get(); // 추후 세션에서 가져오기

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		model.addAttribute("user", userDTO); 
		return "mate/write";
	}

	@PostMapping("/write")
	public String writePost(MateDTO mateDTO, String uid) {
		Optional<User> user_ = userService.findById(uid);
			User user = user_.get();
			mateDTO.setUser(user);

			Mate mate = modelMapper.map(mateDTO, Mate.class);
			Long mid = null;
			
			if(mateDTO.getReqimgs() != null) {
				List<MultipartFile> files = mateDTO.getReqimgs();
				mid = mateService.save(mate, files);
			}else {
				mid = mateService.save(mate);
			}
			
			System.out.println(mateDTO);
			return "redirect:/mate/detail/" + mid;
	}

	@PostMapping("/location")
	@ResponseBody
	public LocationDTO getLocation(LocationDTO param) {
		param = reverseGeocodeUtil.getArea(param);
		return param;
	}
	
	/** Mate Detail */
	@GetMapping("/detail/{mid}")
	public String detail(@PathVariable Long mid, Model model) {
		// 로그인 임시
		User user = userService.findById("admin").get();
		Mate mate = mateService.findById(mid).get();

		MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		model.addAttribute("mate", mateDTO);
		model.addAttribute("user", userDTO); // 로그인 유저
		
		return "mate/detail";
	}
	
	/** Mate Apply */
	@GetMapping("/apply/{mid}")
	public String applySave(@PathVariable Long mid, MateApplyDTO applyDTO) {
		User user = userService.findById(applyDTO.getUserid()).get();
		Mate mate = mateService.findById(applyDTO.getMid()).get();
		
		MateApply apply = modelMapper.map(applyDTO, MateApply.class);
		apply.setUid(user.getIdAuto());
		apply.setMid(mate.getId());
		
		Long aid = applyService.save(apply);
		return "mypage/mate";
	}

	@GetMapping("/apply/cancel/{aid}")
	public String applyCancel(@PathVariable Long aid) {
		applyService.delete(aid);
		return "mypage/mate";
	}

}