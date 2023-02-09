package com.mulcam.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.CSuccessDao;
import com.mulcam.finalproject.dto.MypageSumDTO;
import com.mulcam.finalproject.entity.CSuccess;

@Service
public class CSuccessImpl implements CSuccessService {
	
	@Autowired private CSuccessDao csd;
	
	@Override
	public void insert(CSuccess cs) {
		csd.insert(cs);
	}

	@Override
	public List<CSuccess> getList() {
		List<CSuccess> list = csd.getList();
		return list;
	}

	@Override
	public MypageSumDTO getSum(String uid) {
		MypageSumDTO mypageSumDTO = new MypageSumDTO();
		mypageSumDTO.setChallengeToday(csd.getTodaySum(uid));
		mypageSumDTO.setChallengeWeek(csd.getWeekSum(uid));
		mypageSumDTO.setChallengeMonth(csd.getMonthSum(uid));
		return mypageSumDTO;
			
	}
}