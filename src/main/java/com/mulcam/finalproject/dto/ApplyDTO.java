package com.mulcam.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplyDTO {
	private String id; // uid
	private int mid;
	private String area;
	private String title;
	private String content;
}
