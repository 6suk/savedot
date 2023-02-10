package com.mulcam.finalproject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.entity.Cash;

@Mapper
public interface CashDao{
	
		@Select("select * from cash where cid=#{cid}")
		public Cash getCash(int cid);
		
		@Insert("INSERT INTO cash VALUES(DEFAULT, #{category}, #{regDate}, #{amount}, #{content}, #{memo}, #{uid})")
		public void cashSave(Cash cash);


}
