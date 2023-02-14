package com.mulcam.finalproject.controller;

import java.util.List;

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
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.CSuccessService;
import com.mulcam.finalproject.service.MateApplyService;
import com.mulcam.finalproject.service.UserService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	private CSuccessService css;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MateApplyService applyService;

	/** MyPage 테스트 */
	@GetMapping("/main")
	public String mypageGet(Model model) {
		// 로그인 임시
		User user = userService.findById("admin").get();
		model.addAttribute("user", user);
		return "mypage/mypage";
	}

	@PostMapping("/main")
	@ResponseBody
	public MypageSumDTO mypagePost() {
		MypageSumDTO mypageSumDTO = css.getSum("admin");
		return mypageSumDTO;
	}

	@GetMapping("/mate/apply/{uid}/all")
	public String applyGet(@PathVariable Long uid, Model model) {
		// 로그인 임시
		User user = userService.findById("admin").get();
		model.addAttribute("user", user);
		
		List<MateApplyDTO> list = applyService.findByUid(uid);
		model.addAttribute("apply", list);
		return "mypage/apply_list_all";
	}

}
