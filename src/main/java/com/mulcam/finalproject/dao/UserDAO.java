package com.mulcam.finalproject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.entity.User;

/** 임시 */
@Mapper
public interface UserDAO {
	
	@Select("SELECT * FROM user"
			+ "	WHERE uid = #{uid};")
	public User findByUid(Long uid);
	
	@Select("SELECT * FROM user"
			+ "	WHERE id = #{id};")
	public User findById(String id);
}
