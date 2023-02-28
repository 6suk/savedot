package com.mulcam.finalproject.controller;

import java.time.LocalDate;

import com.mulcam.finalproject.util.DataInputUtil;

public class ConsoleTest {

	public static void main(String[] args) {
//		// 은행 option 제작
//		String bank[] = { "KB국민은행", "IBK기업은행", "NH농협은행", "신한은행", "씨티은행", "SC제일은행", "우리은행", "카카오뱅크", "케이뱅크", "토스뱅크",
//				"하나은행", "경남은행", "광주은행", "대구은행", "부산은행", "KDB산업은행", "수협은행", "우체국은행", "전북은행", "제주은행", "새마을금고", "신협은행",
//				"SBI저축은행", "저축은행", "NH투자증권", "미래에셋증권", "삼성증권", "신한투자증권", "SK증권", "유안타증권", "유진투자증권", "한국투자증권" };
//
//		List<String> list = Arrays.asList(bank);
//		list.forEach(x -> {
//			String data = "<option value='" + x + "'>"
//					+ x + "</option>";
//			System.out.println(data);
//		});

		/** 지출 랜덤 데이터 만들기 */
//		String id[] = { "admin", "ko", "sohee" };
//		int totalPrice[] = { 0, 0, 0 };
//
//		for (int i = 0; i < 100; i++) {
//			int price = DataInputUtil.getPrice(1000,50000);
//			LocalDate date = DataInputUtil.getRandomDate("20220201","20230227");
//			int uid = DataInputUtil.getRandomNum(3, true);
//			System.out.printf("(DEFAULT, 0, '%s', %d, 'test%d', 'test memo%d', '%s'),%n", date, price, i, i, id[uid]);
//			totalPrice[uid] += price;
//		}
//
//		System.out.println(totalPrice[0]);
//		System.out.println(totalPrice[1]);
//		System.out.println(totalPrice[2]);
//		System.out.println(totalPrice[0] + totalPrice[1] + totalPrice[2]);

		/** 챌린지 랜덤 데이터 만들기 */
		String id[] = { "admin", "ko", "hyein", "hani", "haerin", "minji", "sohee" };

		for (int i = 0; i < 5; i++) {
			LocalDate date = DataInputUtil.getRandomDate("20221029", "20221031");
			int cid = DataInputUtil.getRandomNum(3, false);
			int uid = DataInputUtil.getRandomNum(id.length, true);
			System.out.printf("(DEFAULT, '%s', %d, '%s'),%n", date, cid, id[6]);
		}

	}
}
