package com.mulcam.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.mulcam.finalproject.dto.LocationDTO;
import com.mulcam.finalproject.dto.MateApplyDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateApply;
import com.mulcam.finalproject.service.MateApplyService;
import com.mulcam.finalproject.service.MateService;
import com.mulcam.finalproject.service.UserService;
import com.mulcam.finalproject.util.ReverseGeocodeUtil;

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
	public String writeGet() {
		return "mate/write";
	}

	@PostMapping("/write")
	public String writePost(MateDTO mateDTO, String uid) {
		mateDTO.setUser(userService.findById(uid).get());
		Mate mate = modelMapper.map(mateDTO, Mate.class);
		Long mid = null;
		if (mateDTO.getReqimgs() != null) {
			List<MultipartFile> files = mateDTO.getReqimgs();
			mid = mateService.save(mate, files);
		} else {
			mid = mateService.save(mate);
		}
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
		Mate mate = mateService.findById(mid).get();
		MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
		model.addAttribute("mate", mateDTO);
		return "mate/detail";
	}

	/** Mate Apply */
	@PostMapping("/apply/{mid}")
	public String applySave(@PathVariable Long mid, Long uid, MateApplyDTO applyDTO) {
		MateApply apply = modelMapper.map(applyDTO, MateApply.class);
		apply.setUid(uid);
		apply.setMid(mid);
		applyService.save(apply);
		return "redirect:/mypage/mate/apply/" + uid + "/all";
	}

	@GetMapping("/apply/cancel/{uid}/{aid}")
	public String applyCancel(@PathVariable Long aid, @PathVariable Long uid) {
		applyService.delete(aid);
		return "redirect:/mypage/mate/apply/" + uid + "/all";
	}

}