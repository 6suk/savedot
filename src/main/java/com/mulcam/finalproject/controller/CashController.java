package com.mulcam.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.CashImg;
import com.mulcam.finalproject.service.CashService;
import com.mulcam.finalproject.service.UserService;
import com.mulcam.finalproject.util.CashOcrUtil;
import com.mulcam.finalproject.util.ImageUpload;

@Controller
@RequestMapping("/cash")
public class CashController {

	@Autowired
	private CashOcrUtil CashOcrUtil;

	@Autowired
	UserService userService;

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
	public String cashWritePost(Cash cash,HttpServletRequest req) {

		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");

		System.out.println(user);
		cash.setUid(user.getId());
		cashService.cashSave(cash); //cash DB에저장
		System.out.println(cash);
		int cid = cashService.getRecentCid(user.getId()); // cid 받아서 ocr_img cid로 넘겨주기

		List<MultipartFile> saveimg = cash.getSaveimg();
		if (saveimg != null) {
			List<ImageDTO> imgDTO = imageUpload.LocalSaveFiles(saveimg);
			CashImg cashImgInfo = imgDTO.get(0).setCashImgInfo();
			cashImgInfo.setCid(cid);
			System.out.println(cashImgInfo);
			cashService.ocrImgSave(cashImgInfo); // ocr_img DB에 저장
		}

		return "redirect:/mypage/main"; // 나중에 scheduler로 보내주는걸로 수정하기
	}

	@PostMapping("/ocr")
	@ResponseBody
	public ResponseEntity<String> cashOcr(@RequestParam("data") MultipartFile multipartFile) throws Exception {
		return ResponseEntity.ok().body(CashOcrUtil.getOcrResult(multipartFile));
	}


}
