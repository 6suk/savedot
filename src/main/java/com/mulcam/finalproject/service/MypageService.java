package com.mulcam.finalproject.service;

import java.util.List;
import java.util.Map;

import com.mulcam.finalproject.dto.CalendarDTO;
import com.mulcam.finalproject.dto.UserDTO;

public interface MypageService {
	
	/** 날짜에 맞춰 캘린더 정보 및 User 데이터 가져오기 (스탬프 및 지출 수입) */
	public CalendarDTO getCalendar(CalendarDTO calendarDTO);
	
	/** 지출 수입 6개월간 통계 데이터 */
	public Map<String, Object> getCashChart(UserDTO userDTO);
	
	/** 챌린지 6개월간 통계 데이터 */
	public Map<String, Object> getChallengeChart(UserDTO userDTO);
	
	/** 메이트 SavePrice Sum */
	public int getSumSavePriceMate(Long uid);
	
	/** 메이트 지금까지 판매한 개수 */
	public int getSumMate(Long uid);
	
}
