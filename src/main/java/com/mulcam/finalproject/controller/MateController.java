package com.mulcam.finalproject.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.LocationDTO;
import com.mulcam.finalproject.dto.MateApplyDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.MateSearchDTO;
import com.mulcam.finalproject.dto.UserDTO;
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
	public String writeGet(HttpSession session) {
		return "mate/write";
	}

	@PostMapping("/write")
	public String writePost(MateDTO mateDTO, HttpSession session) {
		mateDTO.setUser((UserDTO) session.getAttribute("user"));
		Long mid = mateService.save(mateDTO);

		return "redirect:/mate/detail/" + mid;
	}

	/** Mate Detail */
	@GetMapping("/detail/{mid}")
	public String detail(@PathVariable Long mid, Model model) {
		MateDTO mateDTO = mateService.findOneByMid(mid);
		// 삭제된 게시물일 때 (추후 필터 또는 에러 페이지로 이동하는 로직 구현할 것)
		if (mateDTO == null) {
			return "error/error_404";
		} else {
			model.addAttribute("mate", mateDTO);
			return "mate/detail";
		}

	}

	/** Mate Apply : 신청 */
	@PostMapping("/apply/{mid}")
	public String applySave(@PathVariable Long mid, Long uid, MateApplyDTO applyDTO) {
		MateApply apply = modelMapper.map(applyDTO, MateApply.class);
		apply.setUid(uid);
		apply.setMid(mid);
		System.out.println(apply);
		applyService.save(apply);
		return "redirect:/mypage/mate/apply/" + uid + "/all";
	}

	/** Mate Apply : 신청취소 */
	@GetMapping("/apply/cancel/{uid}/{aid}")
	public String applyCancel(@PathVariable Long aid, @PathVariable Long uid) {
		applyService.delete(aid);
		return "redirect:/mypage/mate/apply/" + uid + "/all";
	}

	/** Mate Apply : 상태변경 */
	@PostMapping("/apply/state-edit")
	@ResponseBody
	public MateApplyDTO applyStateEdit(@RequestBody MateApplyDTO applyDTO) {
		LocalDateTime modDateTime = applyService.editIsApply(applyDTO);
		applyDTO.setModDate(modDateTime);
		return applyDTO;
	}

	/** Mate List */
	@GetMapping("/list")
	public String listSearchGet(@ModelAttribute MateSearchDTO mateSearchDTO, Model model) {
		List<MateDTO> mateDTO = mateService.findAllBySearch(mateSearchDTO);
		model.addAttribute("mate", mateDTO);
		return "mate/list";
	}

	/** Mate Write : 현재위치 (사용안함) */
	@PostMapping("/location")
	@ResponseBody
	public LocationDTO getLocation(LocationDTO param) {
		param = reverseGeocodeUtil.getArea(param);
		return param;
	}

	/** Mate Update : 게시물 수정 */
	/** 추후 본인만 접근 가능하도록 Filter 적용 필요 */
	@GetMapping("/update/{mid}")
	public String updateMateGet(@PathVariable Long mid, Model model) {
		MateDTO mateDTO = mateService.findOneByMid(mid);
		model.addAttribute("mate", mateDTO);
		return "mate/update";
	}

	@PostMapping("/update/{mid}")
	public String updateMatePost(@ModelAttribute MateDTO mateDTO, Model model, HttpSession session) {
		mateDTO.setUser((UserDTO) session.getAttribute("user"));
		mateService.update(mateDTO);
		return "redirect:/mate/detail/" + mateDTO.getMid();
	}

	/** Mate Update : 게시물 삭제 */
	@GetMapping("/delete/{mid}")
	public String deleteMateGet(@PathVariable Long mid) {
		mateService.delete(mid);
		return "redirect:/mate/list";
	}

}