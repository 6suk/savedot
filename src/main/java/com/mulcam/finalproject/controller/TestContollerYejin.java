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
		public String listForm(Model model,Cash cash) {

			LocalDate today = LocalDate.now();
			LocalDate firstDay = today.with(TemporalAdjusters.firstDayOfMonth()); // 해당월의 첫째날을 구해줌

			model.addAttribute("startDate",firstDay);
			model.addAttribute("endDate",today);

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

			List<Cash> list = cashListService.getList(user.getId(), startDate, endDate);

			int incomeSum = 0, expenseSum=0;

			for(Cash cash : list) {
				System.out.println(cash);
				if(cash.getCategory()==0)
					expenseSum += cash.getAmount();
				else
					incomeSum += cash.getAmount();
			}
			System.out.println("수입합계 : " + incomeSum);
			System.out.println("지출합계 : " + expenseSum);

			/* 오늘의 합계 구하기 */

			int expenseTodaySum = cashListService.sumNowExpense(user.getId());
			System.out.println("오늘 지출합계 : " + expenseTodaySum);

			int incomeTodaySum=cashListService.sumNowIncome(user.getId());
			System.out.println("오늘 수입합계 : " + incomeTodaySum);

			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("incomeSum", incomeSum);
			model.addAttribute("expenseSum", expenseSum);
			model.addAttribute("expenseTodaySum", expenseTodaySum);
			model.addAttribute("incomeTodaySum", incomeTodaySum);


			return "cash/list";

}
}
