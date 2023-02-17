package com.mulcam.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.finalproject.dto.ExchangeRateDTO;
import com.mulcam.finalproject.dto.NewsDTO;
import com.mulcam.finalproject.util.ExchangeRateUtil;
import com.mulcam.finalproject.util.NewsUtil;

@Controller
@RequestMapping("/info")
public class InfoController {
    	
	@Autowired private NewsUtil newsUtil;
	@Autowired private ExchangeRateUtil exchangeRateUtil;
	
	@GetMapping("/news")
    public String Information(Model model) throws Exception {
		
		// 경제 뉴스
		List<NewsDTO> list = new ArrayList<>();
		String[] categories = {"금리", "주택", "주식"};
		for (String category: categories) {
			NewsDTO n = newsUtil.getNews(category);
			list.add(n);
		}
		model.addAttribute("newsList", list);
		
		// 환율
		List<ExchangeRateDTO> elist = exchangeRateUtil.getRate();
		model.addAttribute("elist", elist);
		
        return "info/news";
	}
}