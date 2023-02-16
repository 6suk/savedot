package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.entity.CSuccess;
import com.mulcam.finalproject.entity.Challenge;

@Mapper
public interface ChallengeDao {

	@Select("select * from challenge")
	public List<Challenge> getChallengeList();

	@Select("select * from challenge where cid=#{cid}")
	public Challenge getChallenge(int cid);

	@Insert("insert into csuccess values(#{cid})")
	void insert(CSuccess c);

}
