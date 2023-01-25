package com.mulcam.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Test {
	private int ver;
	private String top;
	private String emoji;
	private String etxt;
	private String bottom;
	private String right;
}
