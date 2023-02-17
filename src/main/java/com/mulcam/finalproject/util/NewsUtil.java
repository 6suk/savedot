package com.mulcam.finalproject.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.entity.News;

@Service
public class NewsUtil {

	@Value("${clientId}") private String clientId;
	@Value("${clientSecret}") private String clientSecret;

	public News getNews(String query) throws Exception {

		StringBuilder urlBuilder = new StringBuilder("https://openapi.naver.com/v1/search/news.json");
		urlBuilder.append("?" + URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8"));
		urlBuilder.append("&display=1");

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

		// Read API response
		int responseCode = con.getResponseCode();
		BufferedReader br;
		if (responseCode == 200) { // Success
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // Error
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();

		// Parse API response
		JSONObject json = new JSONObject(response.toString());
		JSONArray items = json.getJSONArray("items");

		JSONObject item = (JSONObject) items.get(0);

		String title = (String) item.get("title");
		String link = (String) item.get("link");
		String description = (String) item.get("description");
		String pubDate = (String) item.get("pubDate");

		News news = new News(title, link, description.replace("<b>", "").replace("</b>", "").replace("http*", ""), pubDate.substring(0,16), query);

		return news;

	}

}
