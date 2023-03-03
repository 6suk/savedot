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
public class User {
	private Long uid;			// pri
	private String uname;			
	private String id;			// uni
	private String pwd;
	private String nickname;		// uni
	private String email;
	
	// NULL
	private String tel;
	private String birthDate;		// not null
	private String postcode;
	private String addr;
	private String detailAddr;
	private int pay;
	private String workIn;
	private String workOut;
	private String departures;
	private String arrivals;
	private String vehicles;
	private int isDeleted;		// not null
	private String bank;
	private String accountNumber;
	private int code;
	private String oauth;
	
}