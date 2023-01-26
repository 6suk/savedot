package com.mulcam.finalproject.controller;

import com.mulcam.finalproject.entity.Test;

public class ConsoleTest {

	
	public static void main(String[] args) {
//		Test test = Test.builder().bottom("기입").emoji("기입").build(); //롬복 전용 메소드라 삭제합니당
		
		Test test = new Test();
		test.setBottom("기입");
		test.setEmoji("기입");
		
		System.out.println(test);

	}

}
