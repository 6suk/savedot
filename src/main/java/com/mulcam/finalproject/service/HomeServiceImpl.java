package com.mulcam.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.ChartDAO;
import com.mulcam.finalproject.dto.HomeDTO;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	private ChartDAO chartDAO;

	@Override
	public HomeDTO getHomeDTO() {
		int index = 0;
		double challengeToday = 0;
		
		/** 챌린지 - 하루동안 아낀 비용 등록되지 않았을 때 전날 데이터 조회 */
		while (true) {
			challengeToday = chartDAO.homeChallengeTodayAllUser(index++);
			if(challengeToday != 0) break;
		}
		
		HomeDTO homeDTO = HomeDTO.builder()
				.challengeAge(chartDAO.homeChallengeTopAge())
				.challengeMonth_(chartDAO.homeChallengeOneMonthAllUser())
				.challengeToday_(challengeToday)
				.challengeType(chartDAO.homeChallengeTopType())
				.matePrice_(chartDAO.homeMateSavePrice())
				.mateTopArea(chartDAO.homeMateTopArea())
				.mateTopDayofWeek(chartDAO.homeMateTopDayOfWeek())
				.build();
		return homeDTO.setResultInt();
	}
	
	

}
