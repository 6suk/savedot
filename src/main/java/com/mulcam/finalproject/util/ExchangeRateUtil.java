package com.mulcam.finalproject.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dto.ExchangeRateDTO;

@Service
public class ExchangeRateUtil {

	@Value("${authKey}")
	private String authKey;

	public List<ExchangeRateDTO> getRateEntity(LocalDate searchLocalDate) {
		List<ExchangeRateDTO> list = new ArrayList<>();
		String searchDate = searchLocalDate.toString().replace("-", "");

		// 환율 가져오기
		String dataType = "AP01";

		StringBuilder urlBuilder = new StringBuilder(
				"https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=");
		urlBuilder.append(authKey + "&searchdate=" + searchDate + "&data=" + dataType);

		// 객체 생성 및 url 연결
		URL url;
		try {
			url = new URL(urlBuilder.toString());
			URLConnection urlConnection = url.openConnection();

			// 데이터 받아오기
			InputStreamReader ir = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader br = new BufferedReader(ir);

			String line;
			line = br.readLine();
			// 받아온 JSON 데이터 파싱하기
			// 파서 객체 만들기
			JSONParser parser = new JSONParser();

			// JSON 데이터를 파싱하여 배열로 추출
			JSONArray items = (JSONArray) parser.parse(line);

			for (Object item2 : items) {
				JSONObject item = (JSONObject) item2;
				String curUnit = (String) item.get("cur_unit"); // 통화코드

				if (curUnit.equals("USD") || curUnit.equals("JPY(100)") || curUnit.equals("EUR")) {
					String curNm = (String) item.get("cur_nm"); // 국가명
					String dealBasR = (String) item.get("deal_bas_r"); // 매매기준율

					ExchangeRateDTO exchangeRate = ExchangeRateDTO.builder().curUnit(curUnit.substring(0, 3))
							.curNm(curNm).dealBasR(dealBasR).build();
					list.add(exchangeRate);
				}
			}
			br.close();
			ir.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ExchangeRateDTO> getRate() {
		// 오늘, 어제 날짜 계산
//		LocalDate today = LocalDate.now();
//		LocalDate yesterday = today.minusDays(1);

		ExchangeRateDTO usd = new ExchangeRateDTO();
		ExchangeRateDTO jpy = new ExchangeRateDTO();
		ExchangeRateDTO eur = new ExchangeRateDTO();

//		List<ExchangeRateDTO> todayEntityList = getRateEntity(today);
//		List<ExchangeRateDTO> yesterdayEntityList = getRateEntity(yesterday);

		/** 예림 : 특정 시간에 데이터 넘어오지 않는 부분 해결(전날, 전전날 데이터 검색) */
		int day = 0, yday = 1;
		List<ExchangeRateDTO> todayEntityList = new ArrayList<>();
		List<ExchangeRateDTO> yesterdayEntityList = new ArrayList<>();
		LocalDate today = LocalDate.now();
		LocalDate yesterday = null;

		while (true) {
			if (todayEntityList.size() == 0) {
				// 오늘 날짜 검색 시 주말, 공휴일이 있을 경우 이전 데이터 가져오기
				today = today.minusDays(day++);
				todayEntityList = getRateEntity(today);
			} else {
				// 어제 날짜 검색 시 주말, 공휴일이 있을 경우 이전 데이터 가져오기
				yesterday = today.minusDays(yday++);
				yesterdayEntityList = getRateEntity(yesterday);
				if(yesterdayEntityList.size() > 0) break;
			}
		}
		/** 예림 : 끝 */

		List<ExchangeRateDTO> elist = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			ExchangeRateDTO todayEntity = todayEntityList.get(i);
			ExchangeRateDTO yesterdayEntity = yesterdayEntityList.get(i);
			String todayCurUnit = todayEntity.getCurUnit();

			ExchangeRateDTO tmp = ExchangeRateDTO.builder()
					.todayCurNm(todayEntity.getCurNm())
					.todayDealBasR(todayEntity.getDealBasR())
					.todayCurUnit(todayEntity.getCurUnit())
					.yesterdayCurUnit(yesterdayEntity.getCurUnit())
					.yesterdayCurNm(yesterdayEntity.getCurNm())
					.yesterdayDealBasR(yesterdayEntity.getDealBasR())
					.today(today) // 예림 : 날짜 추가
					.yesterday(yesterday) // 예림 : 날짜 추가
					.build();
			tmp.setToday(today);	// 요일 추가를 위해 추가

			switch (todayCurUnit) {
			case "USD":
				usd = tmp;
				break;
			case "JPY":
				jpy = tmp;
				break;
			case "EUR":
				eur = tmp;
				break;
			default:
				break;
			}
		}
		usd.setCalc();
		jpy.setCalc();
		eur.setCalc();
		elist.add(usd);
		elist.add(jpy);
		elist.add(eur);
		return elist;
	}

}