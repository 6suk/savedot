package com.mulcam.finalproject.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private Long uid;
	private UserDTO user;
	private Long mid;
	private MateDTO mate;
	private String content;
	private int applyTradelType;
	private String tradeName;
	private int beforeIsApply;
	private int isApply;
	private String isApplyName;
	private int isDel;
	private LocalDateTime modDate;
	private LocalDate regDate;	// 등록일
	private boolean newNotify;

	public void setApplyTradelType(int applyTradelType) {
		String[] tradeNames = { null, "직접거래", "택배거래" };
		this.applyTradelType = applyTradelType;
		this.tradeName = tradeNames[applyTradelType];
	}
	
	public void setIsApply(int isApply) {
		String[] isApplyNames = { "대기중", "거래중", "거래완료" };
		this.isApply = isApply;
		this.isApplyName = isApplyNames[isApply];
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}
	
	public void setAid(String aid) {
		this.aid = Long.parseLong(aid);
	}

	public void setIsApply(String isApply) {
		this.isApply = Integer.parseInt(isApply);
	}

}
