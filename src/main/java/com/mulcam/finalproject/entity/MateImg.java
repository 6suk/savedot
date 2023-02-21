package com.mulcam.finalproject.entity;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
@ToString
public class MateImg {
	private String id;

	private Long mid;
	
	private String ext;

	private String origFileName;

	private String filePath;

	private LocalDate saveDate;
}
