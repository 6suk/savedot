package com.mulcam.finalproject.dto;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
/** 하루 유저 정보 */
public class CalendarInfoDTO {

	/** 유저 */
	private int stamp;
	private int amountPlus;
	private int amountMinus;
	private String plusFormat;
	private String minusFormat;

	/** 날짜 */
	private LocalDate date;
	String type;

	/** 유저 기본값 */
	public CalendarInfoDTO() {
		this.stamp = 0;
		this.amountPlus = 0;
		this.amountMinus = 0;
		this.type = "";
	}

	public void setDate(LocalDate date) {
		this.date = date;
		// 오늘 날짜 세팅
		LocalDate now = LocalDate.now();
		if (now.equals(date)) {
			this.type += " TODAY";
		}
		// 주말 세팅
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int dayOfWeekNumber = dayOfWeek.getValue();
		if (dayOfWeekNumber == 7) {
			this.type += " SUNDAY";
		}
		if (dayOfWeekNumber == 6) {
			this.type += " SATURDAY";
		}
	}

	/** Amount String Format */
	public void SetAmountPlus(int amountPlus) {
		this.amountPlus = amountPlus;
		DecimalFormat decFormat = new DecimalFormat("###,###");
		this.plusFormat = decFormat.format(amountPlus);

	}

	public void SetAmountMinus(int amountMinus) {
		this.amountMinus = amountMinus;
		DecimalFormat decFormat = new DecimalFormat("###,###");
		this.minusFormat = decFormat.format(amountMinus);
	}

}
