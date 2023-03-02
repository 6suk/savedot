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
public class UserDTO {
	private Long uid;			// pri
	private String uname;			
	private String id;			// uni
	private String pwd;
	private String nickname;		// uni
	private String email;
	
	// NULL
	private String tel;
	private String telFormat;
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
	private int sumNowExpense;

	public void setTel(String tel) {
		this.tel = tel;
		if(tel != null) {
			String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
			this.telFormat = tel.replaceAll(regEx, "$1-$2-$3");
		}
	}
}
