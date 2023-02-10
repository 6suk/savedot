package com.mulcam.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.entity.Receipt;
import com.mulcam.finalproject.service.OcrUtil;
import com.mulcam.finalproject.service.ReceiptService;

@Controller
public class ReceiptController {
	
	@Autowired private OcrUtil ocrUtil;
	@Autowired
	ReceiptService receiptService;
	@Value("${naver.accessId}") private String accessId;
	@Value("${naver.secretKey}") private String secretKey;
	@Value("${spring.servlet.multipart.location}")
	private String location;
	

/** 수입지출등록 **/
	
	/* 수입지출 등록 front page띄우기 */
	@GetMapping("/cashsave")
	public String cashRegisterForm() {
		return "/test/cashSave";

	}
	
	@PostMapping("/cashsave") // 수입 지출 등록 
	public String receiptRegister(Receipt receiptVO) {
		receiptVO.setUid(1);
		
		receiptService.receiptSave(receiptVO);
		System.out.println(receiptVO);

		return "";
	}
	
	@PostMapping("/ocr")
	@ResponseBody
	public ResponseEntity<String> cashOcr(@RequestParam("data") MultipartFile multipartFile) throws Exception {
		return ResponseEntity.ok().body(ocrUtil.getOcrResult(multipartFile));
	}
	
	
}
