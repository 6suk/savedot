package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.entity.Cash;

public interface CashListService {
	

	/* 해당 기간별 수입&지출 리스트 */
	List<Cash> getList(String uid, String startDate, String endDate);
	
	/* 오늘 지출 합계 */
	int sumNowExpense(String uid);
	
	/* 오늘 수입 합계 */
	int sumNowIncome(String uid);
	
	/* 리스트내역 출력 (카테고리,내역,금액,메모) */
	List<Cash> getAllCashList(String uid,String regDate);
	

}
