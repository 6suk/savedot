package com.mulcam.finalproject.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.web.multipart.MultipartFile;

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
public class Cash { /* cash db */

	private int cid;
	private int reqCategory; // 0 = 지출 영수증, 1 = 지출, 2 = 수입 (View용)
	private int category; // 0 = 지출, 1 = 수입
	private String regDate;
	private LocalDate cashDateLocal;
	private int amount;
	private String content;
	private String memo;
	private String cashCategoryName;
	private String uid;
	List<MultipartFile> saveimg;

	public void setRegDate(String regDate) {
		this.regDate = regDate;
		this.cashDateLocal = LocalDate.parse(regDate);

	}

	public void setReqCategory(int reqCategory) {
		String[] cashCategoryNames = { "지출", "수입" };
		this.reqCategory = reqCategory;
		this.category = this.reqCategory == 2 ? 1 : 0;
		this.cashCategoryName = cashCategoryNames[this.category];
	}

	public Cash(String regDate, int amount, String content) {
		this.regDate = regDate;
		this.amount = amount;
		this.content = content;
	}



}
