package com.mulcam.finalproject.service;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.UserDAO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.User;

@Service
public class UserServiceImpl implements UserServiceHyerin {
	
	@Autowired ModelMapper modelMapper;
	
	@Autowired private UserDAO userDAO ;
	
	/** 회원가입 */
	@Override
	public void join(User u) {
		String cryptedPwd = BCrypt.hashpw(u.getPwd(), BCrypt.gensalt()); 
		u.setPwd(cryptedPwd);
		userDAO.insert(u);
	}
	
	/** 아이디 중복 검사 */
	public String checkID(String id, String type) { 
	   if(type.equals("user")) {
	      return userDAO.checkIdUser(id);
	   }else if(type.equals("com")) {
	      return userDAO.checkIdCom(id);
	   }
	   
	   return null;
	}
	
	/** 로그인 */
	@Override
	public int login(String id, String pwd, HttpSession session) {
		User user = userDAO.findById(id);
		if (user.getId() != null) {		// id 가 존재
			if (BCrypt.checkpw(pwd, user.getPwd())) {
				session.setAttribute("user", user);		// 세션에 사용자 정보 저장
				return UserServiceHyerin.CORRECT_LOGIN;
			} else {
				return UserServiceHyerin.WRONG_PASSWORD;
			}
		} 		// id 가 없음
		return UserServiceHyerin.ID_NOT_EXIST;
	}

	@Override
	public UserDTO findByUid(Long uid) {
		User user = userDAO.findByUid(uid);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO findById(String id) {
		User user = userDAO.findById(id);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

}
