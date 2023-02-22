package com.mulcam.finalproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.MypageDAO;
import com.mulcam.finalproject.dto.CalendarDTO;
import com.mulcam.finalproject.dto.CalendarInfoDTO;
import com.mulcam.finalproject.entity.CSuccess;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.util.CalendarUtil;

@Service
public class MypageServiceImpl implements MypageService {
	@Autowired
	private MypageDAO mypageDAO;

	@Autowired
	private CalendarUtil calendarUtil;

	@Override
	public CalendarDTO getCalendar(CalendarDTO calendarDTO) {
		/** 캘린더 세팅 */
		calendarDTO = calendarUtil.getCalendar(calendarDTO);

		/** 유저 정보 세팅 */
		Map<Integer, CalendarInfoDTO> infoList = calendarDTO.getInfoList();
		
		/* 스탬프 조회 */
		List<CSuccess> stampList = mypageDAO.getStampByUid(calendarDTO);
		stampList.forEach(stamp -> {
			int key = stamp.getDate().getDayOfMonth();
			CalendarInfoDTO info = infoList.get(key);
			info.setStamp(stamp.getStamp());
		});

		/* 수입지출 조회 */
		List<Cash> cashList = mypageDAO.getCashByUid(calendarDTO);
		cashList.forEach(cash -> {
			int key = cash.getCashDateLocal().getDayOfMonth();
			CalendarInfoDTO info = infoList.get(key);
			if (cash.getCategory() == 0) {
				info.SetAmountMinus(cash.getAmount());
			} else {
				info.SetAmountPlus(cash.getAmount());
			}
		});
		
		calendarDTO.setInfoList(infoList);
		return calendarDTO;
	}
}