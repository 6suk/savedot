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
	private Long uid;
	private String id;
	private String tel;
	private String telFormat;
	private String nickname;

	public void setTel(String tel) {
		this.tel = tel;
		if(tel != null) {
			String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
			this.telFormat = tel.replaceAll(regEx, "$1-$2-$3");
		}
	}

}
