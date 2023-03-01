package com.mulcam.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.MateLike;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.CashListService;
import com.mulcam.finalproject.service.MateLikeService;
import com.mulcam.finalproject.service.MateReplyService;
import com.mulcam.finalproject.service.UserService;

@Controller
public class TestContollerYejin {

	@Autowired
	UserService userService;

	@Autowired
	CashListService cashListService;
	
	@Autowired
	MateReplyService mateReplyService;
	
	@Autowired
	MateLikeService likeService;

	@Value("${naver.accessId}")
	private String accessId;

	@Value("${naver.secretKey}")
	private String secretKey;

	// entity - dao - service - serviceImpl - controller 
	
	@RequestMapping("/mate/like/{mid}")
	public String like(@PathVariable long mid, Model model, HttpServletRequest req,MateLike mateLike) {
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Long uid = user.getUid();
		
		MateLike like = new MateLike(mid,uid);
		likeService.insertLike(mateLike);
		likeService.plusLike(mid);
	
		return "redirect:/mate/detail/" + mid;
	}
	
	@RequestMapping("/mate/delLike/{mid}")
	public String likedel(@PathVariable int mid, Model model, HttpServletRequest req) {
	
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Long uid = user.getUid();
		
		likeService.deleteLike(mid, uid);
		likeService.cancelLike(mid);
	
		return "redirect:/mate/detail/" + mid;
	}
	
	@GetMapping("/mypage/mate/like")
	public String getLikeList(Model model, HttpSession session) {
		
		UserDTO user = (UserDTO) session.getAttribute("user");
		Long uid = user.getUid();
		
		List<MateLike> likeList = likeService.GetLikeList(uid);
		model.addAttribute("likelist",likeList);
		
		return "";
	}
	
		
}
		
		
		

