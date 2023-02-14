package com.mulcam.finalproject.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CSuccess {

	private int sid;
	private LocalDateTime sucDate;
	private int cid;
	private String uid;

}