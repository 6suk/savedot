package com.mulcam.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.finalproject.entity.CSuccess;
import com.mulcam.finalproject.entity.Challenge;
import com.mulcam.finalproject.service.CSuccessService;
import com.mulcam.finalproject.service.ChallengeService;


@Controller
@RequestMapping("/challenge")
public class ChallengeController {

	@Autowired private ChallengeService cs;
	@Autowired private CSuccessService css;

	@GetMapping("/choice")
	public String listForm(Model model) {
		List<Challenge> list = cs.getChallengeList();
		list.forEach(x -> System.out.println(x));
		model.addAttribute("challenge", list);
		return "challenge/choice";
	}

	@GetMapping("/choice/{cid}")
	public String updateForm(@PathVariable int cid, Model model) {
		Challenge c = cs.getChallenge(cid);
		model.addAttribute("c", c);
		System.out.println(c);
		return "challenge/confirm";
	}

	@GetMapping("/confirm")
	public String nameList(Model model) {
		List<Challenge> nameList = cs.getChallengeList();
		model.addAttribute("challenge", nameList);
		return "challenge/confirm";
	}

	@GetMapping("/save/{cid}")
	public String mptest(@PathVariable int cid) {
		CSuccess cs = new CSuccess();
		cs.setUid("admin");
		System.out.println(cid);
		cs.setCid(cid);
		css.insert(cs);
		return "redirect:/mypage";
	}

}