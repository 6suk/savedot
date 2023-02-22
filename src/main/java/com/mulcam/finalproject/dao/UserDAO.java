package com.mulcam.finalproject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.User;

@Mapper
public interface UserDAO {

	/** 혜린 */
	@Select("SELECT * FROM user WHERE uid=#{uid}")
	User findByUid(Long uid);
	
	@Select("SELECT * FROM user WHERE id=#{id}")
	User findById(String id);
	
	@Insert("INSERT INTO user VALUES(default, #{uname}, #{id}, #{pwd}, #{nickname}, #{email}, #{tel}, #{birthDate}, #{addr}, #{pay}, #{departures}, #{arrivals}, #{vehicles})")
	void insert(User u);
	
	// 아이디 중복 검사
	@Select("SELECT id FROM user WHERE id= #{id}")
	String checkIdUser(@Param("id")String id);

	String checkIdCom(String id);
		
//	@Delete("delete from user where id=#{id};")
//	void delete(String id);
	
}
