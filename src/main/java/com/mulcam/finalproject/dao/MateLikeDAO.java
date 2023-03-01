package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.entity.MateLike;

@Mapper
public interface MateLikeDAO {
	
	@Select("SELECT mid FROM mate_like WHERE uid=#{uid} ORDER BY mid DESC")
	List<MateLike> GetLikeList(long uid);
	
	@Insert("INSERT INTO mate_like VALUES(#{mid}, #{uid})")
	void insertLike(MateLike mateLike); 
	
	@Update("UPDATE mate SET likeCnt = likeCnt+1 WHERE mid = #{mid}")
	void plusLike(long mid);
	
	@Update("UPDATE mate SET likeCnt = likeCnt-1 WHERE mid = #{mid}")
	void cancelLike(long mid);
	
	@Delete("DELETE FROM mate_like WHERE mid=#{mid} and uid=#{uid}")
	void deleteLike(long mid,long uid);

}
