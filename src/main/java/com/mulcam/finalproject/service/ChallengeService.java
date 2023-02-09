package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.entity.CSuccess;
import com.mulcam.finalproject.entity.Challenge;

public interface ChallengeService {
	
	List<Challenge> getChallengeList();
	
	public Challenge getChallenge(int cid);
	
	void insert (CSuccess c);
	
}
