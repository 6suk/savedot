package com.mulcam.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.MypageSumDTO;
import com.mulcam.finalproject.service.CSuccessService;

@Controller
public class MypageController {
	@Autowired private CSuccessService css;

	/** MyPage 테스트 */
	@GetMapping("/mypage")
	public String test(Model model) {
		return "mypage/mypage";
	}

	@PostMapping("/mypage")
	@ResponseBody
	public MypageSumDTO get(Model model) {
		MypageSumDTO mypageSumDTO = css.getSum("admin");
		return mypageSumDTO;
	}

}
