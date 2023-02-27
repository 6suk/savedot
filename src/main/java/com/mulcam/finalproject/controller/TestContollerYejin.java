package com.mulcam.finalproject.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.MateReply;
import com.mulcam.finalproject.service.CashListService;
import com.mulcam.finalproject.service.MateReplyService;
import com.mulcam.finalproject.service.UserService;

@Controller
@RequestMapping("/mypage")
public class TestContollerYejin {

	@Autowired
	UserService userService;

	@Autowired
	CashListService cashListService;
	
	@Autowired
	MateReplyService mateReplyService;

	@Value("${naver.accessId}")
	private String accessId;

	@Value("${naver.secretKey}")
	private String secretKey;

	/* 수입지출 등록 리스트 */

		
		/**
		 *  조각메이트 댓글기능 
		 */
		
		@PostMapping("/mate/reply")
		public String reply(HttpServletRequest req, Model model,MateReply reply) {
			
			Long mid = Long.parseLong(req.getParameter("mid"));
			Long uid = Long.parseLong(req.getParameter("uid"));
			String uid2 = req.getParameter("uid");
			String content =req.getParameter("content");

			HttpSession session = req.getSession();
			UserDTO user = (UserDTO) session.getAttribute("user");
			String nickname = user.getNickname();
			
			String sessionUid = user.getId();
			int isMine = (uid2.equals(sessionUid)) ? 1 : 0;
			int grp = 0; // 그룹번호 먼저 생성하는 것 만들기 (댓글기입시 순서대로 생성)
		
			MateReply mateReply = new MateReply(uid, mid, nickname, content, grp, isMine);
			mateReplyService.insertReply(mateReply);
			System.out.println(mateReply);
		
			
			return "mate/detail";
		}
		
}
		
		
		

