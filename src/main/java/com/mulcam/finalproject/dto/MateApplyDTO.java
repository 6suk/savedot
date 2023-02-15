package com.mulcam.finalproject.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mulcam.finalproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MateApplyDTO {

	private Long aid;
	private UserDTO user;
	private MateDTO mate;
	private String content;
	private int applyTradelType;
	private int isApply;
	private int isDel;
	private LocalDateTime modDate;
	private LocalDate regDate;	// 등록일
	
}
