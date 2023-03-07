package com.mulcam.finalproject.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
	private Long uid; // pri
	private String uid_; // pri
	private String uname;
	private String id; // uni
	private String pwd;
	private String pwd2;
	private String nickname; // uni
	private String email;

	// NULL
	private String tel;
	private String telFormat;
	private String birthDate_; // not null
	private LocalDate birthDate; // not null
	private String postcode;
	private String addr;
	private String detailAddr;
	private int pay;
	private String pay_;
	private String workIn;
	private String workOut;
	private String departures;
	private String arrivals;
	private String vehicles;
	private int isDeleted; // not null
	private String bank;
	private String accountNumber;
	private int code;
	private String code_;
	private String oauth; // 카카오로 로그인 한 사람 확인용
	private int sumNowExpense;
	private int likeCount;
	
	public void setBirthDate_(String birthDate_) {
		this.birthDate_ = birthDate_;
		if(birthDate_ != "") {
			this.birthDate = LocalDate.parse(birthDate_);
		}
	}
	
	public void setUid_(String uid_) {
		this.uid_ = uid_;
		if(uid_ != "") {
			this.uid = Long.parseLong(uid_);
		}
	}
	
	public void setPay_(String pay_) {
		this.pay_ = pay_;
		if (pay_ != "") {
			this.pay = Integer.parseInt(pay_.replace(",", ""));
		}
	}

	public void setCode_(String code_) {
		this.code_ = code_;
		if (code_ != "") {
			this.code = Integer.parseInt(code_);
		}
	}

	public void setTel(String tel) {
		this.tel = tel;
		if (tel != null) {
			String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
			this.telFormat = tel.replaceAll(regEx, "$1-$2-$3");
		}
	}

}
