package com.mulcam.finalproject.controller;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.mulcam.finalproject.entity.Test;

public class ConsoleTest {

	public static void main(String[] args) {

		Test test = new Test();
		test.setBottom("기입");
		test.setEmoji("기입");

		System.out.println(test);

		LocalDate localdate = LocalDate.now();
		System.out.println(localdate.toString());

		for (int i = 0; i < 3; i++) {
			UUID uuid = Generators.timeBasedGenerator().generate();
			System.out.println(uuid);
		}
	
		

	}

}
