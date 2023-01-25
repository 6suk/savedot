package com.mulcam.finalproject.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReverseGeocode {

	@Value("${naver.accessId}")
	private String accessId;
	@Value("${naver.secretKey}")
	private String secretKey;

	public String getArea(String latitude, String longitude) {
		// 위도: 35.110912, 경도: 126.8744192, 위치 반환 시간: 1674302556865

		
		StringBuilder sb = new StringBuilder();
		String query = "서울특별시 광진구 자양로 117(자양동)";
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		sb.append("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=")
				.append(longitude + "," + latitude).append("&sourcecrs=epsg:4326&output=json&orders=legalcode");
//		sb.append("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=129.1133567,35.2982640&sourcecrs=epsg:4326&output=json&orders=legalcode");
//		sb.append("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=126.8744192,35.110912&sourcecrs=epsg:4326&output=json&orders=legalcode");

		try {
			URL url = new URL(sb.toString());
			/** 헤더 설정 */
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", accessId);
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", secretKey);
			conn.setDoInput(true);
			/** 응답 결과 확인 */
			int resCode = conn.getResponseCode();
			System.out.println(resCode);

			/** 데이터 수신 */
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			sb.setLength(0);
			String tmpStr = null;
			while ((tmpStr = br.readLine()) != null) {
				sb.append(tmpStr);
			}
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(sb.toString());
			JSONArray results = (JSONArray) obj.get("results");
			JSONObject resultItem = (JSONObject) results.get(0);
			JSONObject region = (JSONObject) resultItem.get("region");
			JSONObject area1 = (JSONObject) region.get("area1");
			String name = area1.get("name").toString();
			System.out.println(name);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

}
