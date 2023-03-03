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
	@Override
	public int idCheck(String id) {
		int cnt = userDAO.idCheck(id);
		return cnt;
	}
	
	/** 닉네임 중복 검사 */
	@Override
	public int nicknameCheck(String nickname) {
		int c = userDAO.nicknameCheck(nickname);
		return c;
	}
	
	/** 로그인 */
	@Override
	public int login(UserDTO user, HttpSession session) {					// 입력한 유저
		UserDTO getUser = findById(user.getId());							// DB에서 가져온 유저
		
		if (getUser != null && getUser.getId() != null) {					// id가 존재
			if (BCrypt.checkpw(user.getPwd(), getUser.getPwd())) {			// 올바른 비밀번호
				session.setAttribute("user", getUser);
				return UserService.CORRECT_LOGIN;
			} else {														// 틀린 비밀번호
				return UserService.WRONG_PASSWORD;
			} 
		}
		return UserService.ID_NOT_EXIST;
	}

	@Override
	public UserDTO findByUid(Long uid) {
		User user = userDAO.findByUid(uid);
		if(user != null) {
			UserDTO userDTO = modelMapper.map(user,UserDTO.class);
			return userDTO;
		}
		return null;
	}

	@Override
	public UserDTO findById(String id) {
		User user = userDAO.findById(id);
		if(user != null) {
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO;
		}
		return null;
	}
	
	/** 회원정보 수정 */
	@Override
	public void update(User user, String newPwd) {
		if(user.getOauth() == null || user.getOauth().equals("")) {		// 일반 로그인 한 유저만 비번 수정 가능
			if (newPwd.length() > 0) {
				String cryptedPwd = BCrypt.hashpw(newPwd, BCrypt.gensalt());
				user.setPwd(cryptedPwd);
				userDAO.update(user);
			} else
				userDAO.updateWithoutPwd(user);
		}
	}
	
	/** 회원 탈퇴 */
	@Override
	public void delete(Long uid) {
		userDAO.delete(uid);
	}

	/** 카카오 로그인 */
	@Override
	public void join(UserDTO kakaoUser) {
		userDAO.insert(kakaoUser);
	}

	@Override
	public int loginKakao(UserDTO kakaoUser, HttpSession session) {
		session.setAttribute("user", kakaoUser);
		return UserService.CORRECT_LOGIN;
	}
}
