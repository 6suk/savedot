package com.mulcam.finalproject.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mulcam.finalproject.dto.KakaoProfile;
import com.mulcam.finalproject.dto.UserDTO;

@Service
public class KakaoService {
	
	@Autowired private UserService userService;
	
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=1dd88828b878b6857e1d2f8e5cf50324");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:8080/user/loginKakao");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("엑세스 토큰 responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }
    
    // 유저 정보 조회
    public UserDTO getUserInfo (String access_Token, HttpSession session) throws Exception {
        
//    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        
        String reqURL = "https://kapi.kakao.com/v2/user/me";

    	URL url = new URL(reqURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        
        //    요청에 필요한 Header에 포함될 내용
        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
        
        int responseCode = conn.getResponseCode();
        System.out.println("로그인 유저 정보 responseCode : " + responseCode);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String line = "";
        String result = "";
        
        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println("로그인 유저 정보 response body : " + result);
        
        kakaoProfile = objectMapper.readValue(result, KakaoProfile.class);

        // User 오브젝트: uname, id, pwd, nickname, email, birthDate)
        System.out.println("유저 이름: " + kakaoProfile.getProperties().nickname);
        System.out.println("유저 아이디(회원번호): " + kakaoProfile.getId());
        UUID garbagePwd = UUID.randomUUID();
        System.out.println("유저 패스워드: " + garbagePwd);
        System.out.println("유저 닉네임: " + kakaoProfile.getProperties().nickname);
        System.out.println("유저 이메일: " + kakaoProfile.getKakao_account().email);
        System.out.println("유저 생일: " + kakaoProfile.getKakao_account().birthday);
        
        // DB에 넣기
        UserDTO kakaoUser = UserDTO.builder()
        		.uname(kakaoProfile.getProperties().nickname)
        		.id(kakaoProfile.getId().toString())
        		.pwd(garbagePwd.toString())
        		.nickname(kakaoProfile.getProperties().nickname)
        		.email(kakaoProfile.getKakao_account().email)
        		.birthDate("0000" + kakaoProfile.getKakao_account().birthday)
        		.build();
        
        // 회원인지 비회원인지 체크해서 처리
        UserDTO originUser = userService.findById(kakaoUser.getId());
        
        if(originUser == null) {
        	System.out.println("기존 회원이 아니므로 자동 회원가입을 진행합니다.");
        	userService.join(kakaoUser);
        }
        
        userService.login(kakaoUser, session);
        System.out.println("자동 로그인을 진행합니다.");
        // 로그인 처리 (세션 등록)
        
        return null;
    }
    
    // 로그아웃
    public void kakaoLogout(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String result = "";
            String line = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}