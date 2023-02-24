package com.mulcam.finalproject.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
/** 한달 캘린더 정보 + 유저 정보 MAP */
public class CalendarDTO {
	/** input - 검색 정보 */
	private String uid;
	private int Y, year;
	private int M, month;
	
	/** info - 오늘 날짜 */
	private int today;
	private LocalDate todayLocalDate;
	
	/** output - 캘린더 세팅 */
	private List<Integer> leftDate;
	private List<Integer> rightDate;
	private List<Integer> dateList;
	
	/** DB - SQL문 */
	private LocalDate start;
	private LocalDate end;
	
	/** output - 날짜별 저장된 유저 정보 */
	private Map<Integer, CalendarInfoDTO> infoList;
	
	/** 기본 : 오늘 날짜 기준 */
	public CalendarDTO() {
		this.todayLocalDate = LocalDate.now();
		this.year = todayLocalDate.getYear();
		this.month = todayLocalDate.getMonthValue();
		this.today = todayLocalDate.getDayOfMonth();
		setSQL();
	}
	
	// 데이트 리스트가 나오면 Map 생성
	public void setDateList(List<Integer> dateList) {
		this.dateList = dateList;
		setMap();
	}
	
	public void setY(int Y) {
		this.Y = Y;
		this.year = Y;
		setSQL();
	}

	public void setM(int M) {
		this.M = M;
		this.month = M;
		setSQL();
	}
	
	public void setSQL() {
		this.start = LocalDate.of(year, month, 1);
		this.end = start.withDayOfMonth(start.lengthOfMonth());
	}
	
	/** info Map 세팅*/
	public void setMap() {
		Map<Integer, CalendarInfoDTO> sendMap = new HashMap<>();
		int key = dateList.size();
		for(int i = 1; i <= key; i++) {
			/** info 객체 검색 결과에 맞춰 LocalDate 추가 */
			CalendarInfoDTO info = new CalendarInfoDTO();
			info.setDate(LocalDate.of(year, month, i));
			
			/** Map 세팅 */
			sendMap.put(i, info);
		}
		this.infoList = sendMap;
	}

}
