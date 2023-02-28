package com.mulcam.finalproject.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class DataInputUtil {

	public static int getRandomNum(int max, boolean zero) {
		int randomNumber = 0;
		if(zero) {
			randomNumber = (int) (Math.random() * max);
		}else {
			randomNumber = (int) (Math.random() * max) + 1;
		}
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
		
		// 시작일
		LocalDate join = LocalDate.of(Integer.parseInt(joinDate.substring(0, 4)),
				Integer.parseInt(joinDate.substring(4, 6)), Integer.parseInt(joinDate.substring(6)));
		
		// 종료일
		LocalDate now = LocalDate.of(Integer.parseInt(nowDate.substring(0, 4)),
				Integer.parseInt(nowDate.substring(4, 6)), Integer.parseInt(nowDate.substring(6)));


		long iRand = random.nextInt(Math.toIntExact(now.toEpochDay()) - Math.toIntExact(join.toEpochDay()) + 1)
				+ Math.toIntExact(join.toEpochDay());

		LocalDate randDate = LocalDate.ofEpochDay(iRand);

		return randDate;
	}
}
