package com.mulcam.finalproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@Builder
public class ChartDTO {
	private String date;
	private String cashMonthUser_;
	private String cashTodayUser_;
	private String cashMonthAll_;
	
	private int cashMonthUser;
	private int cashTodayUser;
	private int cashMonthAll;
	private int ageGroup;
	

	public ChartDTO(String date, String cashMonthUser_, String cashTodayUser_, String cashMonthAll_, int cashMonthUser,
			int cashTodayUser, int cashMonthAll, int ageGroup) {
		this.date = date;
		this.cashMonthUser_ = cashMonthUser_;
		this.cashTodayUser_ = cashTodayUser_;
		this.cashMonthAll_ = cashMonthAll_;
		this.ageGroup = ageGroup;
		
		this.cashMonthUser = Integer.parseInt(cashMonthUser_);
		this.cashTodayUser = Integer.parseInt(cashTodayUser_);
		double cashMonthAllAvg = Double.parseDouble(cashMonthAll_);
		this.cashMonthAll = (int) Math.round(cashMonthAllAvg / 100) * 100;
	}
	
	
	
}
