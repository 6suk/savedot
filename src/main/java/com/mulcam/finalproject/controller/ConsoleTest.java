package com.mulcam.finalproject.controller;

import java.util.Arrays;
import java.util.List;

public class ConsoleTest {

	public static void main(String[] args) {
		// 은행 option 제작
		String bank[] = { "KB국민은행", "IBK기업은행", "NH농협은행", "신한은행", "씨티은행", "SC제일은행", "우리은행", "카카오뱅크", "케이뱅크", "토스뱅크",
				"하나은행", "경남은행", "광주은행", "대구은행", "부산은행", "KDB산업은행", "수협은행", "우체국은행", "전북은행", "제주은행", "새마을금고", "신협은행",
				"SBI저축은행", "저축은행", "NH투자증권", "미래에셋증권", "삼성증권", "신한투자증권", "SK증권", "유안타증권", "유진투자증권", "한국투자증권" };

		List<String> list = Arrays.asList(bank);
		list.forEach(x -> {
			String data = "<option value='" + x + "'>"
					+ x + "</option>";
			System.out.println(data);
		});

	}
}
