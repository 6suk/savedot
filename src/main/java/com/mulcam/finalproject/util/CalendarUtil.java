package com.mulcam.finalproject.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dto.CalendarDTO;

@Service
public class CalendarUtil {

	// 해당 월의 마지막 날짜
	private int getDate(int year, int month) {
		LocalDate leftDate = LocalDate.of(year, month, 1);
		LocalDate lastlastDate = leftDate.withDayOfMonth(leftDate.lengthOfMonth());
		int lastlastDateNumber = lastlastDate.getDayOfMonth();
		return lastlastDateNumber;
	}

	// 해당 월의 첫번째 or 마지막 요일 계산
	private int getDayOfWeek(int year, int month, int Date) {
		LocalDate date = LocalDate.of(year, month, Date);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int dayOfWeekNumber = dayOfWeek.getValue();
		return dayOfWeekNumber % 7;
	}

	public CalendarDTO getCalendar(CalendarDTO calendarDTO) {
		int year = calendarDTO.getYear();
		int month = calendarDTO.getMonth();

		// 해당 월의 시작 요일
		int start = getDayOfWeek(year, month, 1);

		// 해당 월의 마지막 날짜
		int lastDate = getDate(year, month);

		// 해당 월의 마지막 요일
		int lastDayOfWeek = getDayOfWeek(year, month, lastDate);

		// 총 출력 개수 (지난 달, 다음 달 포함)
		int last = lastDate + (6 - lastDayOfWeek);

		// 지난 달 마지막 날짜
		int leftLastDate;

		switch (month) {
		case 1:
			leftLastDate = getDate(year - 1, 12);
			break;
		default:
			leftLastDate = getDate(year, month - 1);
			break;
		}

		List<Integer> left = new ArrayList<>();
		// 시작 요일만큼 지난 달 마지막 날짜 출력
		for (int i = start - 1; i >= 0; i--) {
			left.add(leftLastDate - i);
		}

		// 달력출력
		List<Integer> right = new ArrayList<>();
		List<Integer> dateList = new ArrayList<>();
		int tmp = 1;
		for (int i = 1; i <= last; i++) {
			if (i > lastDate) {
				right.add(tmp++);
			} else {
				dateList.add(i);
				start++;
			}
			if (start % 7 == 0) {
			}
		}

		calendarDTO.setDateList(dateList);
		calendarDTO.setRightDate(right);
		calendarDTO.setLeftDate(left);

		return calendarDTO;
	}
}