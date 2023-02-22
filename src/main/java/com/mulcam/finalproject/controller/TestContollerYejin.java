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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.service.CashListService;
import com.mulcam.finalproject.service.UserService;

@Controller
@RequestMapping("/mypage")
public class TestContollerYejin {

	@Autowired
	UserService userService;

	@Autowired
	CashListService cashListService;

	@Value("${naver.accessId}")
	private String accessId;

	@Value("${naver.secretKey}")
	private String secretKey;

	/* 수입지출 등록 리스트 */

		@GetMapping("cash/list")
		public String getCashList(HttpServletRequest req, Model model) {

			HttpSession session = req.getSession();
			UserDTO user = (UserDTO) session.getAttribute("user");
			int month = LocalDate.now().getMonthValue();
			model.addAttribute("month",month);
			LocalDate today = LocalDate.now();
			
			/* 사용자 지정 기간별 수입 지출 합계구하기 */
			String startDate = req.getParameter("startDate");
			if(startDate==null || startDate.equals(""))
				startDate = today.with(TemporalAdjusters.firstDayOfMonth()).toString();
			String endDate = req.getParameter("endDate");
			if(endDate==null || endDate.equals(""))
				endDate = today.toString();
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			
			List<Cash> list = cashListService.getList(user.getId(), startDate, endDate);
			int incomeSum = 0, expenseSum=0;
			for(Cash cash : list) {
				if(cash.getCategory()==0)
					expenseSum += cash.getAmount();
				else
					incomeSum += cash.getAmount();
			}
			model.addAttribute("incomeSum", incomeSum); // 달의 첫째날 ~ 오늘날짜까지의 수입합 
			model.addAttribute("expenseSum", expenseSum); // 달의 첫째날 ~ 오늘날짜까지의 지출합 
			
			
			/* 오늘의 지출수입 합계 구하기 */
			int expenseTodaySum = cashListService.sumNowExpense(user.getId());
			int incomeTodaySum=cashListService.sumNowIncome(user.getId());
			model.addAttribute("expenseTodaySum", expenseTodaySum); // 오늘 지출합
			model.addAttribute("incomeTodaySum", incomeTodaySum);   // 오늘 수입합
			
			/* 한달치 리스트 출력 */  
			Map<String, List<Cash>> map = cashListService.getAllCashList(user.getId());
			model.addAttribute("map",map);

			return "cash/list";
		}
}
