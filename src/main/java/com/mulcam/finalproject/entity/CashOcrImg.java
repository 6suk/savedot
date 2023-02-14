package com.mulcam.finalproject.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @Builder
public class CashOcrImg {

	private int oid;
	private String imgFileName;
	private String ext;
	private LocalDateTime imgRegDate;
	private String imgFilePath;
	private int cid;
	private String uid;


}
