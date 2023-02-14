package com.mulcam.finalproject.controller;

import java.util.List;

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

import com.mulcam.finalproject.dto.ImageDTO;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.CashImg;
import com.mulcam.finalproject.service.CashService;
import com.mulcam.finalproject.util.CashOcrUtil;
import com.mulcam.finalproject.util.ImageUpload;

@Controller
@RequestMapping("/cash")
public class CashController {

	@Autowired
	private CashOcrUtil CashOcrUtil;

	@Autowired
	CashService cashService;

	@Value("${naver.accessId}")
	private String accessId;

	@Value("${naver.secretKey}")
	private String secretKey;

	@Value("${spring.servlet.multipart.location}")
	private String location;

	@Autowired
	ImageUpload imageUpload;

	/** 수입지출등록 **/
	/* 수입지출 등록 front page띄우기 */
	@GetMapping("/write")
	public String cashWriteGet() {
		return "cash/write";
	}

	@PostMapping("/write") // 수입 지출 등록
	public String cashWritePost(Cash cash) {
		List<MultipartFile> saveimg = cash.getSaveimg();
		if (saveimg != null) {
			List<ImageDTO> imgDTO = imageUpload.LocalSaveFiles(saveimg);
			CashImg cashImgInfo = imgDTO.get(0).setCashImgInfo();
			System.out.println(cashImgInfo);
//			cashService.ocrImgSave(cashImgInfo);
			// 해당 DB 저는 없어서 잠시 주석처리 해두었습니다!
		}
		System.out.println(cash);
		cash.setUid("ko");
		cashService.cashSave(cash);
		return "redirect:/cash/write";
	}

	@PostMapping("/ocr")
	@ResponseBody
	public ResponseEntity<String> cashOcr(@RequestParam("data") MultipartFile multipartFile) throws Exception {
		return ResponseEntity.ok().body(CashOcrUtil.getOcrResult(multipartFile));
	}

}
