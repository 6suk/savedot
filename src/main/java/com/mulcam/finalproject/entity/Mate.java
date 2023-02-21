package com.mulcam.finalproject.entity;

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
public class Mate {
	private Long mid;

	private Long uid;
	
	private int state;

	private int category;

	private String bank;

	private String accountNumber;

	private int telType;
	private String telUrl;

	private String title;

	private String content;

	private int price1;

	private int price2;

	private int positionNum;
	
	private int positonApplyNum;

	private String openChat;

	private int tradeType;

	private String placeName;

	private String placeCoords;
	
	private String placeAddr;
	
	private int placeCode;

	private int parcelType;

	private int parcelPrice;

	private LocalDateTime modDate;

	private int isDel;

	private int viewCnt;

	private int replyCnt;

	private int likeCnt;
	

}
