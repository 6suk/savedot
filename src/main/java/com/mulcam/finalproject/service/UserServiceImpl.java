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
public class UserServiceImpl implements UserService {
	
	@Autowired ModelMapper modelMapper;
	
	@Autowired private UserDAO userDAO ;
	
	/** 회원가입 */
	@Override
	public void join(User user) {
		String cryptedPwd = BCrypt.hashpw(user.getPwd(), BCrypt.gensalt()); 
		user.setPwd(cryptedPwd);
		userDAO.insert(user);
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
		UserDTO user = findById(id);
		if (user != null && user.getId() != null) {		// id 가 존재
			if (BCrypt.checkpw(pwd, user.getPwd())) {
				session.setAttribute("user", user);
				return UserService.CORRECT_LOGIN;
			} else {
				return UserService.WRONG_PASSWORD;
			}
		} 		// id 가 없음
		return UserService.ID_NOT_EXIST;
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
	
//	/** 회원정보 수정 */
//	@Override
//	public void update(User user) {
//		userDAO.insert(user);
//	}
	
	/** 회원 탈퇴 */
	@Override
	public void delete(String id) {
		userDAO.delete(id);
	}

}
