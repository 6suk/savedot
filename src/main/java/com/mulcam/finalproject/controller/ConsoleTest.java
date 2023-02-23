package com.mulcam.finalproject.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

import com.mulcam.finalproject.util.CalendarUtil;

public class ConsoleTest {
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in); 

	    System.out.print("연도 입력 : "); 
	    int year = sc.nextInt(); 

	    System.out.print("월 입력 : "); 
	    int month = sc.nextInt(); 
//
//		int year = 2023;
//		int month = 2;
	    CalendarUtil calendarUtil = new CalendarUtil();
//	    calendarUtil.getCalendar(year,month); 
	    
		//기준일자
		LocalDate date = LocalDate.of(year, month, 1);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int dayOfWeekNumber = dayOfWeek.getValue();

		//해당 월의 첫째 날
		LocalDate firstDate = date.withDayOfMonth(1);
		firstDate.getDayOfMonth();

		//해당 월의 마지막 날
		LocalDate lastDate = date.withDayOfMonth(date.lengthOfMonth());
		
		// 지난 달 마지막 날짜
		LocalDate leftDate = LocalDate.of(year, 1, 1);
		LocalDate lastlastDate = leftDate.withDayOfMonth(leftDate.lengthOfMonth());
		int lastlastDateNumber = lastlastDate.getDayOfMonth();
		
//		System.out.println(lastlastDateNumber);
//		System.out.println(firstDate);
//		System.out.println(lastDate);
//		System.out.println(dayOfWeekNumber);
	    
	}
}
