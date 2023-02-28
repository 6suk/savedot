package com.mulcam.finalproject.service;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.ProfileDAO;
import com.mulcam.finalproject.entity.Profile;

@Service
public class ProfileService {

	@Autowired private ProfileDAO profileDAO;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	
	public void insert(Profile profile, HttpSession session) {
		profileDAO.insert(profile);
		setSessionValue(profile, session);
	}
	
	public Profile getProfileById(String id) {
		Profile profile = profileDAO.getProfileById(id);
		return profile;
	}
	
	// 로그인할 경우 사용
	public void setAsideValue(String id, HttpSession session) {
		Profile profile = profileDAO.getProfileById(id);
		if (profile == null)
			return;
		setSessionValue(profile, session);
	}

	public void update(Profile profile, HttpSession session) {
		profileDAO.update(profile);
		setSessionValue(profile, session);
	}
	
	public void setSessionValue(Profile profile, HttpSession session) {
		if (profile.getFilename() != null)
			session.setAttribute("sessionFilename", profile.getFilename());
	}
	
	public JSONObject makeJsonProfile(Profile profile) {
		JSONObject obj = new JSONObject();
		obj.put("filename", profile.getFilename());
		return obj;
	}
	
	public String getTodayQuote() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(uploadDir + "/data/todayQuote.txt"), 1024);
		int index = (int) Math.floor(Math.random() * 100);
		String result = null;
		for (int i=0; i<=index; i++)
			result = br.readLine();
		br.close();
		return result;
	}

	
}