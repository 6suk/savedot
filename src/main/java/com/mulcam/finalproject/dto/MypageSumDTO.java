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
	int challengeToday;
	int challengeWeek;
	int challengeMonth;
	int mateToday;
	int mateWeek;
	int mateMonth;
}
