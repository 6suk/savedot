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
public class LocationDTO {
	private String lat; // 위도
	private String lon; // 경도
	private String timestamp;
	private String area1;
	private String area2;
}
