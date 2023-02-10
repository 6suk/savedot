package com.mulcam.finalproject.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cash { /* cash db */

	private int cid;
	private int category;// 0 = OCR, 지출,1 = 수입 
	private String regDate;
	private LocalDate cashDateLocal;
	private int amount;
	private String content; 
	private String memo;
	private String cashCategoryName;
	private String uid;
	
	

	public void setRegDate(String regDate) {
		this.regDate = regDate;
		this.cashDateLocal = LocalDate.parse(regDate);
		
	}
	
	public void setCategory(int category) {
		String[] CashCategoryNames = {"지출", "수입"};
		this.category = category;
		cashCategoryName = CashCategoryNames[category];
	}

	public Cash(String regDate, int amount, String content) {
		this.regDate = regDate;
		this.amount = amount;
		this.content = content;
	}
	

	
}

	