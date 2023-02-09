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

import com.mulcam.finalproject.dto.LocationDTO;

import net.bytebuddy.asm.Advice.Return;

@Service
public class ReverseGeocodeUtil {

	@Value("${naver.accessId}")
	private String accessId;
	@Value("${naver.secretKey}")
	private String secretKey;

	public LocationDTO getArea(LocationDTO param) {
		StringBuilder sb = new StringBuilder();

		sb.append("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=")
				.append(param.getLon() + "," + param.getLat())
				.append("&sourcecrs=epsg:4326&output=json&orders=legalcode");

		try {
			URL url = new URL(sb.toString());
			/** 헤더 설정 */
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", accessId);
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", secretKey);
			conn.setDoInput(true);
			/** 응답 결과 확인 */
			int resCode = conn.getResponseCode();
			if (resCode == 200) {
				/** 데이터 수신 */
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				sb.setLength(0);
				String tmpStr = null;
				while ((tmpStr = br.readLine()) != null) {
					sb.append(tmpStr);
				}
				br.close();
			} else {
				System.out.println("[오류] : " + resCode);
			}

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
			JSONObject area1_ = (JSONObject) region.get("area1");
			JSONObject area2_ = (JSONObject) region.get("area2");
			String area1 = area1_.get("name").toString();
			String area2 = area2_.get("name").toString();
			param.setArea1(area1);
			param.setArea2(area2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return param;
	}
}
