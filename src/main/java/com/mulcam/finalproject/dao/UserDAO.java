package com.mulcam.finalproject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.entity.User;

@Mapper
public interface UserDAO {

	@Select("SELECT * FROM user WHERE uid=#{uid}")
	User findByUid(Long uid);
	
	@Select("SELECT * FROM user WHERE id=#{id} and isDeleted=0")
	User findById(String id);
	
	@Insert("INSERT INTO user VALUES(default, #{uname}, #{id}, #{pwd}, #{nickname}, #{email}, #{tel}, #{birthDate}, #{addr}, #{pay}, #{departures}, #{arrivals}, #{vehicles})")
	void insert(User u);
	
	/** 아이디 중복 검사 */
	@Select("SELECT id FROM user WHERE id= #{id}")
	String checkIdUser(@Param("id")String id);

	String checkIdCom(String id);
	
//	/** 회원정보 수정 */
//	@Update("UPDATE user SET nickname=#{nickname}, email=#{email}, tel=#{tel}, birthDate=#{birthDate}, addr=#{addr}, pay=#{pay}, departures=#{departures}, arrivals=#{arrivals}, vehicles=#{vehicles}")
//	void update(User u);
	
	/** 회원 탈퇴 */
	@Update("UPDATE user SET isDeleted=1 WHERE id=#{id}")
	public void delete(String id);
}
