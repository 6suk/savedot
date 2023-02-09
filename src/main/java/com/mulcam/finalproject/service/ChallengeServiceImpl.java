package com.mulcam.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.ChallengeDao;
import com.mulcam.finalproject.entity.CSuccess;
import com.mulcam.finalproject.entity.Challenge;

@Service
public class ChallengeServiceImpl implements ChallengeService {
	
	@Autowired private ChallengeDao challengeDao;
	
	
	@Override
	public List<Challenge> getChallengeList() {
		List<Challenge> list = challengeDao.getChallengeList();
		return list;
	}
	@Override
	public Challenge getChallenge(int cid) {
		Challenge list = challengeDao.getChallenge(cid);
		return list;
	}
	@Override
	public void insert(CSuccess c) {
		challengeDao.insert(c);
	}

}
