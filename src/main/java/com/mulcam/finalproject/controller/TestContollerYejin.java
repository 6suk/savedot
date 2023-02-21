package com.mulcam.finalproject.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	//리스트폼띄워주기
		@GetMapping("cash/list")
		public String listForm(Model model,HttpServletRequest req) {
			HttpSession session = req.getSession();
			UserDTO user = (UserDTO) session.getAttribute("user");

			LocalDate today = LocalDate.now();
			LocalDate firstDay = today.with(TemporalAdjusters.firstDayOfMonth()); // 해당월의 첫째날을 구해줌
			int month = LocalDate.now().getMonthValue();

			model.addAttribute("startDate",firstDay);
			model.addAttribute("endDate",today);
			model.addAttribute("month",month);
			
			/* 한달 지출 합계, 수입합계 */
			List<Cash> list = cashListService.getList(user.getId(), firstDay.toString(), today.toString());
			int incomeSum = 0, expenseSum=0;
			for(Cash cash : list) {
				System.out.println(cash);
				if(cash.getCategory()==0)
					expenseSum += cash.getAmount();
				else
					incomeSum += cash.getAmount();
			}
			model.addAttribute("incomeSum", incomeSum);
			model.addAttribute("expenseSum", expenseSum);
			
			
			/* 오늘의 지출수입 합계 구하기 */
			int expenseTodaySum = cashListService.sumNowExpense(user.getId());
			int incomeTodaySum=cashListService.sumNowIncome(user.getId());
			model.addAttribute("expenseTodaySum", expenseTodaySum);
			model.addAttribute("incomeTodaySum", incomeTodaySum);
			
			
			/* 한달치 리스트 출력 */ 
			List<Cash> allCashList = cashListService.getAllCashList(user.getId());
			model.addAttribute("allCashList",allCashList);
			System.out.println("========리스트======= " + allCashList );
			return "cash/list";

		}


		// 리스트 보여주기
		@PostMapping("cash/list") //
		public String postCashList(HttpServletRequest req, Model model) {

			HttpSession session = req.getSession();
			UserDTO user = (UserDTO) session.getAttribute("user");

			/* 사용자 지정 기간별 수입 지출 합계구하기 */
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		
	



			return "cash/list";

}
}
