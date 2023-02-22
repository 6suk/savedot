package com.mulcam.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.CalendarDTO;
import com.mulcam.finalproject.dto.MateApplyDTO;
import com.mulcam.finalproject.dto.MypageSumDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.service.CSuccessService;
import com.mulcam.finalproject.service.MateApplyService;
import com.mulcam.finalproject.service.MypageService;
import com.mulcam.finalproject.util.CalendarUtil;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	private CSuccessService css;

	@Autowired
	private MateApplyService applyService;
	
	@Autowired
	private MypageService mypageService;

	/** MyPage */
	@GetMapping("/main")
	public String mypageGet(@ModelAttribute("calendar") CalendarDTO calendarDTO, Model model, HttpSession session) {
		UserDTO user = (UserDTO)session.getAttribute("user");
		calendarDTO.setUid(user.getId());
		calendarDTO = mypageService.getCalendar(calendarDTO);
		System.out.println(calendarDTO);		
		return "mypage/mypage";
	}

	@PostMapping("/main")
	@ResponseBody
	public MypageSumDTO mypagePost(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		MypageSumDTO mypageSumDTO = css.getSum(user.getId());
		return mypageSumDTO;
	}

	@GetMapping("/mate/apply/all")
	public String applyGet(Model model, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		Long uid = user.getUid();
		
		List<MateApplyDTO> sendApply = applyService.findBySendUid(uid);
		model.addAttribute("sendApply", sendApply);
		model.addAttribute("sendNew", applyService.findNewBySendUid(uid)); // New Notify

		List<MateApplyDTO> getApply = applyService.findByGetUid(uid);
		model.addAttribute("getApply", getApply);
		model.addAttribute("getNew", applyService.findNewByGetUid(uid)); // New Notify

		return "mypage/apply_list_all";
	}

}
