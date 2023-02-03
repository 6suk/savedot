package com.mulcam.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString
@Builder
public class MypageSumDTO {
	private int challengeToday;
	private int challengeWeek;
	private int challengeMonth;
	private int mateToday;
	private int mateWeek;
	private int mateMonth;
}
