package com.mulcam.finalproject.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.ChartDAO;
import com.mulcam.finalproject.dao.MypageDAO;
import com.mulcam.finalproject.dto.CalendarDTO;
import com.mulcam.finalproject.dto.CalendarInfoDTO;
import com.mulcam.finalproject.dto.ChartDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.CSuccess;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.util.CalendarUtil;

@Service
public class MypageServiceImpl implements MypageService {
	@Autowired
	private MypageDAO mypageDAO;

	@Autowired
	private CalendarUtil calendarUtil;

	@Autowired
	ChartDAO chartDAO;

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

	@Override
	public List<ChartDTO> getCashChart(UserDTO userDTO) {
		int month = 6;
		int ageGroup = getAge(userDTO.getBirthDate());
		List<Map<String, Object>> cashMonthUser = chartDAO.findCashMonthSumByUid(month, userDTO.getId());
		List<Map<String, Object>> cashTodayUser = chartDAO.findCashDateSumByUid(month, userDTO.getId());
		List<Map<String, Object>> cashMonthAll = chartDAO.findCashMonthAvgAllByAgeGroup(month, ageGroup);
		List<ChartDTO> allData = new ArrayList<>();

		for(int i = 0; i < month; i++) {
			Map<String, Object> map1 = cashMonthUser.get(i);
			Map<String, Object> map2 = cashTodayUser.get(i);
			Map<String, Object> map3 = cashMonthAll.get(i);
			
			ChartDTO data = ChartDTO.builder()
					.date(map1.get("date").toString())
			.cashMonthUser_(map1.get("sum").toString())
			.cashTodayUser_(map2.get("sum").toString())
			.cashMonthAll_(map3.get("avg").toString())
			.ageGroup(ageGroup)
			.build();
			
			allData.add(data);
		}
		return allData;
	}
	
	/** 나이대 구하기 */
	public int getAge(String inputAge) {
		 //현재 년도 구하기
		 Calendar now = Calendar.getInstance(); //년월일시분초
		 Integer currentYear = now.get(Calendar.YEAR);
			 
		 //태어난년도를 위한 세팅
		 Integer birthYear = Integer.parseInt(inputAge.substring(0,4));

		 // 현재 년도 - 태어난 년도 => 나이 (만나이X)
	     int age = (currentYear - birthYear +1);
	     String outputAge = Integer.toString(age);
	     
	     // 나이대 구하기
	     int ageGroup = Math.round(Integer.parseInt(outputAge)/ 10) * 10;
	        
	 	 return ageGroup;
	}
}
