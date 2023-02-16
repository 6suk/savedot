package com.mulcam.finalproject.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class MateApply {

	private Long aid;

	private Long uid;

	private Long mid;

	private String content;

	private LocalDate regDate;	// 등록일
	
	private LocalDateTime modDate;	// isApply 갱신일

	private int applyTradelType;

	private int isDel;

	private int isApply;
}
