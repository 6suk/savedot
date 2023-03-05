package com.mulcam.finalproject.service;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
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
	public Map<String, Object> getCashChart(UserDTO userDTO) {
		int month = 6;
		int ageGroup = getAge(userDTO.getBirthDate());
		String uid = userDTO.getId();
		Map<String, Object> allData = getDate(month); // View 전송용 Map 만들기
		allData.put("ageGroup", ageGroup);

		// data1 : 지출 내역(User)
		chartDAO.cashMonthByUid(month, uid).forEach(x -> {
			String key = x.get("date").toString();
			ChartDTO dto = (ChartDTO) allData.get(key);
			dto.setData1(getResult(x));
		});

		// data2 : 지출 내역 현재일자(User)
		chartDAO.cashMonthDateByUid(month, uid).forEach(x -> {
			String key = x.get("date").toString();
			ChartDTO dto = (ChartDTO) allData.get(key);
			dto.setData2(getResult(x));
		});

		// data3 : 지출 내역(All / 나이대)
		chartDAO.cashMonthAllGroupAge(month, ageGroup).forEach(x -> {
			String key = x.get("date").toString();
			ChartDTO dto = (ChartDTO) allData.get(key);
			dto.setData3(getResult(x));
		});
		return allData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getChallengeChart(UserDTO userDTO) {
		int month = 6;
		int ageGroup = getAge(userDTO.getBirthDate());
		String uid = userDTO.getId();
		Map<String, Object> allData = getDate(month); // View 전송용 Map 만들기
		allData.put("ageGroup", ageGroup);
		JSONObject data4 = new JSONObject();
		JSONObject data5 = new JSONObject();

		// data1 : 챌린지로 아낀비용(User)
		chartDAO.challengeMonthByUid(month, uid).forEach(x -> {
			String key = x.get("date").toString();
			ChartDTO dto = (ChartDTO) allData.get(key);
			dto.setData1(getResult(x));
		});

		// data2 : 챌린지로 아낀비용 현재일자(User)
		chartDAO.challengeMonthDateByUid(month, uid).forEach(x -> {
			String key = x.get("date").toString();
			ChartDTO dto = (ChartDTO) allData.get(key);
			dto.setData2(getResult(x));
		});

		// data3 : 챌린지로 아낀비용(All / 나이대)
		chartDAO.challengeMonthAllGroupAge(month, ageGroup).forEach(x -> {
			String key = x.get("date").toString();
			ChartDTO dto = (ChartDTO) allData.get(key);
			dto.setData3(getResult(x));
		});

		// data4 : 많이 참여한 챌린지(User)
		chartDAO.challengeTypeMonthByUid(month, uid).forEach(x -> {
			int sum = getResult(x);
			data4.put((int) x.get("cid"), sum);
		});

		// data5 : 많이 참여한 챌린지(All / 나이대)
		chartDAO.challengeTypeMonthAllGroupAge(month, ageGroup).forEach(x -> {
			int avg = getResult(x);
			data5.put((int) x.get("cid"), avg);
		});

		allData.put("data4", data4);
		allData.put("data5", data5);
		return allData;
	}
	
	public int getAge(LocalDate inputAge) {
		return getAge(inputAge.toString());
	}

	/** 나이대 구하기 */
	public int getAge(String inputAge) {
		// 현재 년도 구하기
		Calendar now = Calendar.getInstance(); // 년월일시분초
		Integer currentYear = now.get(Calendar.YEAR);

		// 태어난년도를 위한 세팅
		Integer birthYear = Integer.parseInt(inputAge.substring(0, 4));

		// 현재 년도 - 태어난 년도 => 나이 (만나이X)
		int age = (currentYear - birthYear + 1);
		String outputAge = Integer.toString(age);

		// 나이대 구하기
		int ageGroup = Math.round(Integer.parseInt(outputAge) / 10) * 10;

		return ageGroup;
	}

	/** 날짜별 맵 생성 */
	public Map<String, Object> getDate(int month) {
		Map<String, Object> result = new HashMap<>();
		LocalDate today = LocalDate.now();
		int index = month - 1;

		for (int i = 0; i < month; i++) {
			ChartDTO chartDTO = new ChartDTO();
			chartDTO.setIndex(index--);
			String strDate = today.minusMonths(i).format(DateTimeFormatter.ofPattern("yyyy-MM"));
			result.put(strDate, chartDTO);
		}
		return result;
	}

	/** sum 또는 avg에 맞춰 int 값 생성 */
	public int getResult(Map<String, Object> input) {
		int result = 0;

		if (input.containsKey("sum")) {
			result = Integer.parseInt(input.get("sum").toString());
		}
		if (input.containsKey("avg")) {
			double cashMonthAllAvg = Double.parseDouble(input.get("avg").toString());
			if (cashMonthAllAvg > 100) {
				result = (int) Math.round(cashMonthAllAvg / 100) * 100;
			} else {
				result = (int) Math.round(cashMonthAllAvg);
			}
		}
		return result;
	}

}
