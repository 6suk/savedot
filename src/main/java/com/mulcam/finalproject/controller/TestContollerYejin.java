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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Mate;
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
	
	@ResponseBody
	@RequestMapping("/mate/like/{mid}")
	public boolean like(@PathVariable int mid, HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		Long uid = user.getUid();
		MateLike like = new MateLike(mid, uid);
		likeService.insertLike(like);
		likeService.plusLike(mid);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/mate/delLike/{mid}")
	public boolean likedel(@PathVariable int mid, HttpServletRequest req) {
	
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		Long uid = user.getUid();
		
		likeService.deleteLike(mid, uid);
		likeService.cancelLike(mid);
	
		return true;
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
		
		
		

