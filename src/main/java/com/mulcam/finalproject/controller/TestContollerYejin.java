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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.MateReply;
import com.mulcam.finalproject.service.CashListService;
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

	@Value("${naver.accessId}")
	private String accessId;

	@Value("${naver.secretKey}")
	private String secretKey;

		/**
		 *  조각메이트 댓글기능 
		 */
		
		@PostMapping("/mate/reply/insert")
		public String insertReply(HttpServletRequest req, Model model,MateReply reply) {
			
			long mid = Long.parseLong(req.getParameter("mid")); 
			long uid = Long.parseLong(req.getParameter("uid"));
			String uid2 = req.getParameter("uid"); 
			String content =req.getParameter("content");
			

			HttpSession session = req.getSession();
			UserDTO user = (UserDTO) session.getAttribute("user");
			String nickname = user.getNickname();
			String sessionUid = user.getId();
			int isMine = (uid2.equals(sessionUid)) ? 1 : 0;
			
			MateReply mateReply = new MateReply(uid, mid, nickname, content, isMine);
			System.out.println(mateReply);
			mateReplyService.insertReply(mateReply);
	
			
			return "redirect:/mate/detail/" + mid;
		}
		
		
		/* 댓글 삭제 */
		@GetMapping("/mate/reply/delete/{rid}/{mid}")
		public String deleteReply(HttpServletRequest req,Model model, @PathVariable long rid, @PathVariable long mid) {
			mateReplyService.deleteReply(rid);
			return "redirect:/mate/detail/" + mid;
		}
		
		/* 대댓글달기 */
		@GetMapping("/mate/rereply/{rid}/{grp}")
		public String getRreplyform(HttpServletRequest req, Model model,@PathVariable long rid, @PathVariable int grp) {
			MateReply mateReply = mateReplyService.getGrp(rid, grp);
			model.addAttribute("mateReply",mateReply);
			return "mate/mate_rereply";
		}
		
		
		@PostMapping("/mate/rereply")
		public String insertRereply(HttpServletRequest req, Model model,MateReply reply) {
			
			long mid = Long.parseLong(req.getParameter("mid")); // 게시글 번호 
			long uid = Long.parseLong(req.getParameter("uid"));
			String content =req.getParameter("content");
			String uid2 = req.getParameter("uid"); 
			int grp = Integer.parseInt(req.getParameter("grp")); 
			
			HttpSession session = req.getSession();
			UserDTO user = (UserDTO) session.getAttribute("user");
			String nickname = user.getNickname();
			String sessionUid = user.getId();
			int isMine = (uid2.equals(sessionUid)) ? 1 : 0;
			
			MateReply mateReply = new MateReply(uid, mid, nickname, content, isMine,grp);
			mateReplyService.insertReReply(mateReply);

			return "redirect:/mate/detail/" + mid;
		}
		
		/* 댓글 수정 */
		@GetMapping("/mate/reply/update/{rid}")
		public String updateReplyform(HttpServletRequest req, Model model,@PathVariable long rid) {
			MateReply mateReply = mateReplyService.getMateReply(rid);
			model.addAttribute("mateReply",mateReply);
			return "mate/updateReply";
		}
		
		@PostMapping("/mate/reply/update") 
		public String updateReply(HttpServletRequest req, Model model){
			long mid = Long.parseLong(req.getParameter("mid"));
			long rid = Long.parseLong(req.getParameter("rid"));
			String content =req.getParameter("content");
			
			MateReply mateReply = new MateReply(rid,content);
			mateReplyService.updateReply(mateReply);
			
			return "redirect:/mate/detail/" + mid;
		}
		
}
		
		
		

