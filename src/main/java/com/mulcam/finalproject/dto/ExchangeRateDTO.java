package com.mulcam.finalproject.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExchangeRateDTO {

	private String curUnit;
	private String curNm;
	private String dealBasR;
	private LocalDate today;			// 예림 : 날짜 추가
	private String todayCurUnit;		// 통화코드
	private String todayCurNm;			// 국가명
	private String todayDealBasR;		// 오늘 매매기준율
	private LocalDate yesterday;		// 예림 : 날짜 추가
	private String yesterdayCurUnit;	// 통화코드
	private String yesterdayCurNm;		// 국가명
	private String yesterdayDealBasR;	// 전일 매매기준율
	private BigDecimal subDeal; 		// 전일대비
	private BigDecimal fluctuationRate; 	// 등락률

	public void setCalc() {
		if (this.todayDealBasR == null || this.todayDealBasR == null || this.todayDealBasR == null) {
			this.todayCurUnit = getYesterdayCurUnit();
			this.todayCurNm = getYesterdayCurNm();
			this.todayDealBasR = getYesterdayDealBasR();
			this.subDeal = new BigDecimal("0");
			this.fluctuationRate = new BigDecimal("0");

		} else {
			// 전일대비 계산
			BigDecimal t = new BigDecimal(todayDealBasR.replace(",", ""));
			BigDecimal y = new BigDecimal(yesterdayDealBasR.replace(",", ""));
			this.subDeal =  t.subtract(y);

			// 등락률 계산
			BigDecimal percent = new BigDecimal("100");
			this.fluctuationRate = this.subDeal.multiply(percent).divide(y, 2, BigDecimal.ROUND_HALF_UP);
		}
	}
}