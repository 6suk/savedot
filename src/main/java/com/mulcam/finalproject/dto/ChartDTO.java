package com.mulcam.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChartDTO {
	private int index;
	private int data1 = 0;
	private int data2 = 0;
	private int data3 = 0;
	private int data4 = 0;
	private int data5 = 0;
}
