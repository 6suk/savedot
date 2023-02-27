package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.dto.CalendarDTO;
import com.mulcam.finalproject.dto.ChartDTO;
import com.mulcam.finalproject.dto.UserDTO;

public interface MypageService {
	
	/** 날짜에 맞춰 캘린더 정보 및 User 데이터 가져오기 (스탬프 및 지출 수입) */
	public CalendarDTO getCalendar(CalendarDTO calendarDTO);
	
	/** 6개월간 통계 데이터 */
	public List<ChartDTO> getCashChart(UserDTO userDTO);
	
}
