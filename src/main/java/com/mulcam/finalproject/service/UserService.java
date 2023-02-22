package com.mulcam.finalproject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.UserDAO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.User;

/** 임시 */
@Service
public class UserService {
	@Autowired

	ModelMapper modelMapper;
	
	@Autowired
	UserDAO userDAO;


	/** id로 찾기 */
	public UserDTO findById(String id) {
		User user = userDAO.findById(id);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	/** PK로 찾기 */
	public UserDTO findByUid(Long id) {
		User user = userDAO.findByUid(id);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

}

