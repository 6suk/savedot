package com.mulcam.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.Test;
import com.mulcam.finalproject.service.MateService;
import com.mulcam.finalproject.service.ReverseGeocode;

@Controller
public class TestController {
	
	@Autowired
	ReverseGeocode reverseGeocode;
	
	@Autowired
	MateService mateService;
	
//	@GetMapping("/jpa")
//	public String jpaGet() {
//		Mate mate = Mate.builder()
//				.area("광주광역시")
//				.category(0)
//				.content("샬라샬라")
//				.price1(12000)
//				.price2(6000)
//				.title("title");
//		
//		mateService.save(null);
//		return null;
//	}
	
	/** Reverse Geocode 테스트 */
	@GetMapping("/rege")
	@ResponseBody
	public String reGeoCode() {
		String lat = "35.110912";//위도
		String lon = "126.8744192";//경도
		
		return reverseGeocode.getArea(lat,lon);
	}
	
	/** MyPage 테스트 */
	@GetMapping("/test")
	public String test(Model model) {
		return "test";
	}
	
	@PostMapping("/test")
	@ResponseBody
	public Test get(Model model, int ver) {
		List<Test> list = new ArrayList<>();
		Test test1 = new Test();
		test1.setVer(0);
		test1.setTop("오늘");
		test1.setEmoji("poultry-leg_1f357.png");
		test1.setEtxt("치킨 0.1마리");
		test1.setBottom("약 0,000원을 아꼈어요!");
		test1.setRight("주간");
		
		Test test2 = new Test();
		test2.setVer(1);
		test2.setTop("한주간");
		test2.setEmoji("beer-mug_1f37a.png");
		test2.setEtxt("맥주 0.1마리");
		test2.setBottom("약 0,000원을 아꼈어요!");
		test2.setRight("월간");
		
		Test test3 = new Test();
		test3.setVer(2);
		test3.setTop("한달간");
		test3.setEmoji("red-apple_1f34e.png");
		test3.setEtxt("0.01 아이패드");
		test3.setBottom("약 33,000원을 아꼈어요!");
		test3.setRight("일간");
		
		
		list.add(test1);
		list.add(test2);
		list.add(test3);
		
		model.addAttribute("data", list.get(ver));
		return list.get(ver);
	}
	
	
	
}
