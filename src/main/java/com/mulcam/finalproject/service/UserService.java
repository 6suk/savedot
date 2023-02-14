package com.mulcam.finalproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	/** id로 찾기 */
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}
	
	/** PK로 찾기 */
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
}
