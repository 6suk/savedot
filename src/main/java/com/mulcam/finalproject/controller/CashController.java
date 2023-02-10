package com.mulcam.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.service.CashOcrUtil;
import com.mulcam.finalproject.service.CashService;

@Controller
@RequestMapping("/cash")
public class CashController {
	
	@Autowired private CashOcrUtil CashOcrUtil;
	@Autowired
	CashService cashService;
	@Value("${naver.accessId}") private String accessId;
	@Value("${naver.secretKey}") private String secretKey;
	@Value("${spring.servlet.multipart.location}")
	private String location;
	

/** 수입지출등록 **/
	
	/* 수입지출 등록 front page띄우기 */
	@GetMapping("/write")
	public String cashRegisterForm() {
		return "cash/write";

	}
	
	@PostMapping("/write") // 수입 지출 등록 
	public String cashRegister(Cash cash,MultipartFile saveimg) {
		cash.setUid("ko");
		cashService.cashSave(cash);
		System.out.println(cash);
		System.out.println(saveimg);

		return null;
	}
	
	@PostMapping("/ocr")
	@ResponseBody
	public ResponseEntity<String> cashOcr(@RequestParam("data") MultipartFile multipartFile) throws Exception {
		return ResponseEntity.ok().body(CashOcrUtil.getOcrResult(multipartFile));
	}
	
	
}
