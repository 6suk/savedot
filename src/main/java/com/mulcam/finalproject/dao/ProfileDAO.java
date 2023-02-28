package com.mulcam.finalproject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.entity.Profile;

@Mapper
public interface ProfileDAO {

	@Insert("INSERT INTO profile VALUES (#{id}, #{image}, #{size}, #{filename})")
	void insert(Profile profile);
	
//	@Select("SELECT * FROM profile WHERE uid=#{uid}")
//	Profile getProfile(Long uid);
	
	@Select("SELECT * FROM profile WHERE id=#{id}")
	Profile getProfileById(String id);
	
	@Update("UPDATE profile SET image=#{image}, size=#{size}, filename=#{filename} WHERE id=#{id}")
	void update(Profile profile);
	
}