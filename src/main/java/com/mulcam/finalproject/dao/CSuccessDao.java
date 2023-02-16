package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.entity.CSuccess;

@Mapper
public interface CSuccessDao {

	@Select("select * from csuccess")
	public List<CSuccess> getList();

	@Insert("insert into csuccess values(default, default, #{cid}, #{uid})")
	void insert(CSuccess cs);
	
	@Select("SELECT IFNULL(SUM(camount),0) sumToday "
			+ " FROM csuccess AS s"
			+ " JOIN challenge AS c"
			+ " ON c.cid = s.cid"
			+ " WHERE CAST(s.sucDate AS DATE) = CURDATE() AND uid = #{uid};")
	public int getTodaySum(String uid);
	
	@Select("SELECT IFNULL(SUM(camount),0) sumWeek"
			+ "	FROM csuccess AS s"
			+ "	JOIN challenge AS c"
			+ "	ON c.cid = s.cid"
			+ "	WHERE CAST(s.sucDate AS DATE) BETWEEN DATE_ADD(NOW(), INTERVAL -1 WEEK) AND NOW() AND uid = #{uid};")
	public int getWeekSum(String uid);
	
	
	@Select("SELECT IFNULL(SUM(camount),0) sumMonth"
			+ "	FROM csuccess AS s"
			+ "	JOIN challenge AS c"
			+ "	ON c.cid = s.cid"
			+ "	WHERE CAST(s.sucDate AS DATE) BETWEEN DATE_ADD(NOW(), INTERVAL -1 MONTH) AND NOW() AND uid = #{uid};")
	public int getMonthSum(String uid);

}
