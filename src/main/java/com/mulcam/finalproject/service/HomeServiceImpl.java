package com.mulcam.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.HomeDAO;
import com.mulcam.finalproject.dto.HomeDTO;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	private HomeDAO homeDAO;

	@Override
	public HomeDTO getHomeDTO() {
		int index = 0;
		double challengeToday = 0;
		
		/** 챌린지 - 하루동안 아낀 비용 등록되지 않았을 때 전날 데이터 조회 */
		while (true) {
			challengeToday = homeDAO.findOneChallengeByToday(index++);
			if(challengeToday != 0) break;
		}
		
		HomeDTO homeDTO = HomeDTO.builder()
				.challengeAge(homeDAO.findOneChallengeByAgeGroup())
				.challengeMonth_(homeDAO.findOneChallengeByMonth())
				.challengeToday_(challengeToday)
				.challengeType(homeDAO.findOneChallengeByType())
				.matePrice_(homeDAO.findOneMateSavePrice())
				.mateTopArea(homeDAO.findOneMateTopArea())
				.mateTopDayofWeek(homeDAO.findOneMateTopUploadDayOfWeek())
				.build();
		return homeDTO.setResultInt();
	}
	
	

}
