package com.mulcam.finalproject.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.service.UserServiceHyerin;
import com.mulcam.finalproject.session.UserSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource private UserSession userSession;

	@Autowired private UserServiceHyerin userService;
	
	
	/** 회원가입 */
	@GetMapping("/join")
	public String joinForm() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(HttpServletRequest req) {
		String uname = req.getParameter("uname").strip();
		String id = req.getParameter("id").strip();
		String pwd = req.getParameter("pwd").strip();
		String pwd2 = req.getParameter("pwd2").strip();
		String nickname = req.getParameter("nickname").strip();
		String email = req.getParameter("email").strip();
		String tel = req.getParameter("tel").strip();
		String birthDate = req.getParameter("birthDate").strip();
		String addr = req.getParameter("addr").strip();
		String strpay = req.getParameter("pay").strip();
		int pay = 0;
		if (strpay != null && !strpay.equals("")) {
			pay = Integer.parseInt(strpay.replace(",", ""));
		}
		String departures = req.getParameter("departures").strip();
		String arrivals = req.getParameter("arrivals").strip();
		String vehicles = req.getParameter("vehicles").strip();

		
		System.out.println(id);
		 
		if (pwd.equals(pwd2)) {
			User u = new User(0L, uname, id, pwd, nickname, email, tel, birthDate, addr, pay, departures, arrivals, vehicles);
			userService.join(u);
			return "redirect:/user/login";
		} else {
			System.out.println("패스워드 입력이 잘못되었습니다.");
			return "redirect:/user/join";
		}
	}
	
	/** 아이디, 닉네임 중복확인 */
	   @PostMapping("/join/checkid")
	   @ResponseBody
	   public int checkid(@RequestParam("id") String id, @RequestParam("type") String type) {

//	    System.out.println("ajax 완료 : "+id);
//	    String check = userService.checkID(id);
//	    System.out.println("중복검사 : "+a);

	      if (id.equals(userService.checkID(id, type))) {
	         return 1;
	      }
	      return 0;
	   }
	
	   
	/** 로그인 */
	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest req, RedirectAttributes rttr, Model model) {
		HttpSession session = req.getSession();
		
		String idFailMessage = "존재하지 않는 아이디입니다.";
		String pwdFailMessage = "잘못된 비밀번호입니다.";
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		int result = userService.login(id, pwd, session);
		switch (result) {
		case UserServiceHyerin.CORRECT_LOGIN:
			UserDTO userDTO = userService.findById(id);
			session.setAttribute("user", userDTO);
			return "redirect:/mypage/main";
			
		case UserServiceHyerin.WRONG_PASSWORD:
			rttr.addFlashAttribute("loginFail", pwdFailMessage);
			return "user/login";
		
		case UserServiceHyerin.ID_NOT_EXIST:
			rttr.addFlashAttribute("loginFail", idFailMessage);
			return "redirect:/user/join";
		
		default:
			return "";
		}

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
}