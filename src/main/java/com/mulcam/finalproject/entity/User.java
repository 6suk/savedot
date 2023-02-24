package com.mulcam.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private Long uid;			// pri
	private String uname;			
	private String id;			// uni
	private String pwd;
	private String nickname;		// uni
	private String email;
	
	// NULL
	private String tel;
	private String birthDate;
	private String addr;
	private int pay;
	private String departures;
	private String arrivals;
	private String vehicles;
	private int isDeleted;		// not null
	
}