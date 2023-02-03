package com.mulcam.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dto.LocationDTO;
import com.mulcam.finalproject.dto.MypageSumDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.Test;
import com.mulcam.finalproject.service.MateService;
import com.mulcam.finalproject.service.ReverseGeocodeUtil;

@Controller
public class TestControllerYelim {

	@Autowired
	ReverseGeocodeUtil reverseGeocode;

	@Autowired
	MateService mateService;

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
		dto3.setLat("33.49975371296835"); //위도
		dto3.setLon("126.51487649468552"); //경도
		System.out.println("제주특별자치도 제주시 : " + reverseGeocode.getArea(dto3));

		LocationDTO dto4 = new LocationDTO();
		dto4.setLat("36.001686461440805"); // 위도
		dto4.setLon("129.55788156481418"); // 경도
		System.out.println("경상북도 포항시 남구 : " + reverseGeocode.getArea(dto4));

		return "";
	}

	/** MyPage 테스트 */
	@GetMapping("/mypage")
	public String test() {
		return "test/mypage";
	}

	@PostMapping("/mypage")
	@ResponseBody
	public MypageSumDTO get() {
		// 로그인 유저 uid 받기가 있어야함. (아직 로그인 로직 없으니 임시로 설정할 것)
		MypageSumDTO mypageSumDTO = MypageSumDTO.builder()	// 실제로는 서비스단에서 받아야함
				.challengeToday(8000)
				.challengeWeek(30000)
				.challengeMonth(125400)
				.mateToday(5000)
				.mateWeek(15000)
				.mateMonth(50000)
				.build();
		return mypageSumDTO;
	}

}
