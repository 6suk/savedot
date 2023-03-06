package com.mulcam.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dao.ChartDAO;
import com.mulcam.finalproject.dto.ImageDTO;
import com.mulcam.finalproject.dto.LocationDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.MateSearchDTO;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.CashImg;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.AlarmService;
import com.mulcam.finalproject.service.MateApplyService;
import com.mulcam.finalproject.service.MateService;
import com.mulcam.finalproject.service.MypageService;
import com.mulcam.finalproject.service.UserService;
import com.mulcam.finalproject.util.ImageUploadUtil;
import com.mulcam.finalproject.util.ReverseGeocodeUtil;

@Controller
public class TestControllerYelim {

	@Autowired
	ReverseGeocodeUtil reverseGeocode;

	@Autowired
	MateService mateService;

	@Autowired
	UserService userService;

	@Autowired
	ImageUploadUtil imageUpload;

	@Autowired
	MateApplyService applyService;
	
	@Autowired
	ChartDAO chartDAO;
	
	@Autowired
	MypageService mypageService;
	
	@Autowired
	private AlarmService alarmService;
	


//	@GetMapping("/login/{uid}")
//	public String loginTemp(@PathVariable String uid, HttpSession session) {
//		UserDTO userDTO = userService.findById(uid);
//		session.setAttribute("user", userDTO);
//		return "redirect:/mypage/main";
//	}
//
//	@GetMapping("/logout")
//	public String logoutTemp(HttpSession session) {
//		session.invalidate();
//		return "redirect:/mypage/main";
//	}

	
	/** MyPage : 알림 */
//	@GetMapping("/test")
//	public String AlarmGet(HttpSession session) {
//		session.setAttribute("user", userService.findById("sohee"));
//		return "test/test";
//	}
	
//	@GetMapping("/test")
//	public String test(HttpSession ss) {
//		ss.setAttribute("user", userService.findById("sohee"));
//		return "redirect:/mypage/chart/challenge";
//	}
	@GetMapping("/cashsave/test")
	public String datatestGet() {
		return "cashsave/write";
	}

	@PostMapping("/cashsave/test")
	public String datatest(Cash cash) {
		List<MultipartFile> saveimg = cash.getSaveimg();
		if (saveimg != null) {
			List<ImageDTO> imgDTO = imageUpload.LocalSaveFiles(saveimg);
			CashImg receiptImgInfo = imgDTO.get(0).setCashImgInfo();
			System.out.println(receiptImgInfo);
		}

		System.out.println(cash);
		return "redirect:/cashsave/test";
	}

	@GetMapping("/mate/test")
	public String writetest() {
		User user = new User();
		user.setId("ko");
		user.setPwd("ko");
		user.setNickname("프로 알뜰러");
		user.setTel("01012345678");
		User user2 = new User();
		user2.setId("admin");
		user2.setPwd("admin");
		user2.setNickname("관리자");
		user2.setTel("01012345689");
//		userService.save(user2);
//		userService.save(user);

	//	User u = userService.findById("ko").get();
	//	userService.delete(u);
		return "test/write";
	}

	/** Reverse Geocode 테스트 */
	@GetMapping("/rege")
	@ResponseBody
	public String reGeoCode() {
		LocationDTO dto1 = new LocationDTO();
		dto1.setLat("37.28699209406186"); // 위도
		dto1.setLon("127.01184649535573"); // 경도

		System.out.println("경기도 수원시 장안구 : " + reverseGeocode.getArea(dto1));

		LocationDTO dto2 = new LocationDTO();
		dto2.setLat("33.322779705889445"); // 위도
		dto2.setLon("126.8423825110494"); // 경도
		System.out.println("제주특별자치도 서귀포시 : " + reverseGeocode.getArea(dto2));

		LocationDTO dto3 = new LocationDTO();
		dto3.setLat("33.49975371296835"); // 위도
		dto3.setLon("126.51487649468552"); // 경도
		System.out.println("제주특별자치도 제주시 : " + reverseGeocode.getArea(dto3));

		LocationDTO dto4 = new LocationDTO();
		dto4.setLat("36.001686461440805"); // 위도
		dto4.setLon("129.55788156481418"); // 경도
		System.out.println("경상북도 포항시 남구 : " + reverseGeocode.getArea(dto4));

		return "";
	}

	/** MyPage 테스트 */
//	@GetMapping("/mypage")
//	public String test() {
//		return "test/mypage";
//	}
//
//	@PostMapping("/mypage")
//	@ResponseBody
//	public MypageSumDTO get() {
//		// 로그인 유저 uid 받기가 있어야함. (아직 로그인 로직 없으니 임시로 설정할 것)
//		MypageSumDTO mypageSumDTO = MypageSumDTO.builder()	// 실제로는 서비스단에서 받아야함
//				.challengeToday(8000)
//				.challengeWeek(30000)
//				.challengeMonth(125400)
//				.mateToday(5000)
//				.mateWeek(15000)
//				.mateMonth(50000)
//				.build();
//		return mypageSumDTO;
//	}

}
