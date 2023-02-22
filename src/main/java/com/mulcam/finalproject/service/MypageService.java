package com.mulcam.finalproject.service;

import com.mulcam.finalproject.dto.CalendarDTO;

public interface MypageService {
	
	/** 날짜에 맞춰 캘린더 정보 및 User 데이터 가져오기 (스탬프 및 지출 수입) */
	public CalendarDTO getCalendar(CalendarDTO calendarDTO);

}
