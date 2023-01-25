package com.mulcam.finalproject.controller;

import com.mulcam.finalproject.entity.Test;

public class ConsoleTest {

	
	public static void main(String[] args) {
		Test test = Test.builder().bottom("기입").emoji("기입").build();
		System.out.println(test);

	}

}
