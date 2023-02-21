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
	private Long uid;

	private String id;

	private String pwd;

	private String tel;

	private String nickname;
}
