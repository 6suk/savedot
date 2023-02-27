package com.mulcam.finalproject.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class DataInputUtil {

	public static int getRandomNum(int max) {
		int randomNumber = (int) (Math.random() * max);
		return randomNumber;
	}

	public static int getPrice(int min, int max) {
		if(min == 0 || max == 0) {
			min = 1000; // 최소값
			max = 100000; // 최대값
		}
		int randomNumber = (int) (Math.random() * (max - min) + min);
		return Math.round(randomNumber / 100) * 100;
	}

	public static LocalDate getRandomDate(String joinDate, String nowDate) {
		Random random = new Random();
//		String joinDate = "20230101";

		// 1. 현재 날짜
//		LocalDate now = LocalDate.now();

		// 2. 등록 날짜
		// LocalDate.of() : 날짜 셋팅
		LocalDate join = LocalDate.of(Integer.parseInt(nowDate.substring(0, 4)),
				Integer.parseInt(joinDate.substring(4, 6)), Integer.parseInt(joinDate.substring(6)));
		
		LocalDate now = LocalDate.of(Integer.parseInt(nowDate.substring(0, 4)),
				Integer.parseInt(nowDate.substring(4, 6)), Integer.parseInt(nowDate.substring(6)));

		// 3. 현재 날짜 - 등록 날짜 = 개월
		long month = join.until(now, ChronoUnit.MONTHS);

		// 4. 현재 날짜 - 4개월 전 날짜
		LocalDate ago = now.minusMonths(month);

		// 5. 4번 날짜 ~ 등록 날짜 사이의 랜덤 날짜
		// toEpochDay() : 기준 시로부터의 일 수(타임 스탬프와 비슷한 개념)
		long iRand = random.nextInt(Math.toIntExact(ago.toEpochDay()) - Math.toIntExact(join.toEpochDay()) + 1)
				+ Math.toIntExact(join.toEpochDay());

		// 랜덤 일수(iRand)를 날짜로 변환
		LocalDate randDate = LocalDate.ofEpochDay(iRand);

		// 출력을 원하는 포멧으로 변환
		String tmp = randDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		return randDate;
	}
}
