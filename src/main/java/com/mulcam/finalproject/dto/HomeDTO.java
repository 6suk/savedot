package com.mulcam.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class HomeDTO {
	// 원가 기준 평균 세이브 금액
	private double matePrice_;	
	private int matePrice;	
	
	// 거래가 가장 많이 올라오는 요일
	private String mateTopDayofWeek;
	
	// 만남 거래가 가장 활발한 지역
	private String mateTopArea;
	
	// 전체 유저 한달동안 아낀 비용 평균
	private double challengeMonth_;
	private int challengeMonth;
	
	// 전체 유저 하루동안 아낀 비용 평균
	private double challengeToday_;
	private int challengeToday;
	
	// 가장 참여율이 높은 나이대
	private int challengeAge;
	
	// 가장 많이 참여한 챌린지
	private String challengeType;
	
	public HomeDTO setResultInt() {
		this.matePrice = (int) matePrice_;
		this.challengeMonth = (int) challengeMonth_;
		this.challengeToday = (int) challengeToday_;
		return this;
	}
}
