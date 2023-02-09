package com.mulcam.finalproject.controller;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.mulcam.finalproject.entity.Test;
import com.mulcam.finalproject.service.ReverseGeocodeUtil;

public class ConsoleTest {

	public static void main(String[] args) {

		LocalDate localdate = LocalDate.now();
		System.out.println(localdate.toString());

		for (int i = 0; i < 3; i++) {
			UUID uuid = Generators.timeBasedGenerator().generate();
			System.out.println(uuid);
		}
		
		Test today = new Test();
		today.setting(1, 8000);
		Test week = new Test();
		week.setting(2, 30000);
		Test month = new Test();
		month.setting(3, 125400);
		

		System.out.println(today);
		System.out.println(week);
		System.out.println(month);
	

	}

}
