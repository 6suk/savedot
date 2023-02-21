package com.mulcam.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.MateApplyDTO;
import com.mulcam.finalproject.dto.MypageSumDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.service.CSuccessService;
import com.mulcam.finalproject.service.MateApplyService;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	private CSuccessService css;

	@Autowired
	private MateApplyService applyService;

	/** MyPage 테스트 */
	@GetMapping("/main")
	public String mypageGet(Model model) {
		return "mypage/mypage";
	}

	@PostMapping("/main")
	@ResponseBody
	public MypageSumDTO mypagePost(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		MypageSumDTO mypageSumDTO = css.getSum(user.getId());
		return mypageSumDTO;
	}

	@GetMapping("/mate/apply/{uid}/all")
	public String applyGet(@PathVariable Long uid, Model model, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		if(user == null || !user.getUid().equals(uid)) {
			return "redirect:/mate/write";
		}

		List<MateApplyDTO> sendApply = applyService.findBySendUid(uid);
		model.addAttribute("sendApply", sendApply);
		model.addAttribute("sendNew", applyService.findNewBySendUid(uid));	// New Notify

		List<MateApplyDTO> getApply = applyService.findByGetUid(uid);
		model.addAttribute("getApply", getApply);
		model.addAttribute("getNew", applyService.findNewByGetUid(uid));	// New Notify

		return "mypage/apply_list_all";
	}



}
