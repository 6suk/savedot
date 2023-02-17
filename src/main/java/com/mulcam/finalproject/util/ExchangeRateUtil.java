package com.mulcam.finalproject.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.entity.ExchangeRate;

@Service
public class ExchangeRateUtil {

	@Value("${authKey}") private String authKey;

	public List<ExchangeRate> getRate() throws Exception {

		// 오늘, 어제 날짜 계산
		String searchDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

		Date yDate = new Date();
		yDate = new Date(yDate.getTime()+(1000*60*60*24*-1));
		SimpleDateFormat ySdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String yesterday = ySdf.format(yDate);

		// 환율 가져오기
		String dataType = "AP01";

		StringBuilder urlBuilder = new StringBuilder(
				"https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=");
		urlBuilder.append(authKey + "&searchdate=" + searchDate + "&data=" + dataType);

		// 객체 생성 및 url 연결
		URL url = new URL(urlBuilder.toString());
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

		List<ExchangeRate> elist = new ArrayList<>();

		for (Object item2 : items) {
			JSONObject item = (JSONObject) item2;

			String cur_unit = (String) item.get("cur_unit");		// 통화코드
			String cur_nm = (String) item.get("cur_nm");			// 국가명
			String deal_bas_r = (String) item.get("deal_bas_r");	// 매매기준율

			if (cur_unit.equals("USD") || cur_unit.equals("JPY(100)") || cur_unit.equals("EUR")) {
				ExchangeRate exchangeRate = new ExchangeRate(cur_unit.substring(0, 3), cur_nm, deal_bas_r);
				elist.add(exchangeRate);
			}
		}

		br.close();
		ir.close();

		return elist;
	}

}
