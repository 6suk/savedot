package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.entity.MateApply;

@Mapper
public interface MateApplyDAO {
	
	@Insert("INSERT INTO mate_apply VALUES(DEFAULT, #{content}, DEFAULT, DEFAULT, DEFAULT, #{mid}, #{uid}, #{applyTradelType})")
	public void save(MateApply mateApply);
	
	@Select("SELECT LAST_INSERT_ID();")
	public Long findSaveId();
	
	@Update("UPDATE mate_apply"
			+ "	SET isDel = 1"
			+ "	WHERE aid = #{aid};")
	public void delete(Long aid);
	
	@Select("SELECT * FROM mate_apply WHERE isDel = 0;")
	public List<MateApply> findAll();
	
	@Select("SELECT * FROM mate_apply WHERE uid = #{uid} AND isDel = 0;")
	public List<MateApply> findByUid(Long uid);
	
	@Select("SELECT * FROM mate_apply WHERE mid = #{mid} AND isDel = 0;")
	public List<MateApply> findByMid(Long mid);
	
}
