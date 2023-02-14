package com.mulcam.finalproject.dto;

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
	private String userid; // view 전송용
	private User user;
	private Long mid;	// view 전송용
	private MateDTO mate;
	private String content;
	private int applyTradelType;
	private int isApply;
	private int isDel;
	private LocalDateTime modDate;
	
}
