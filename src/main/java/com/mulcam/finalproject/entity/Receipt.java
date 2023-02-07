package com.mulcam.finalproject.entity;

import java.sql.Date;
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
public class Receipt {

	private int cashRid;
	private int category;// 0 = OCR , 1 = 지출, 2 = 수입 ( 1=지출,2=수입)
	private String cashDate;
	private LocalDate cashDateLocal;
	private int amount;
	private String cashContent; 
	private String memo;
	private String cashCategoryName;
	private int uid;
	
	

	public void setCashDate(String cashDate) {
		this.cashDate = cashDate;
		this.cashDateLocal = LocalDate.parse(cashDate);
		
	}
	
	public void setCategory(int category) {
		String[] CashCategoryNames = {"OCR", "지출", "수입"};
		this.category = category;
		cashCategoryName = CashCategoryNames[category];
	}

	public Receipt(String cashDate, int amount, String cashContent) {
		this.cashDate = cashDate;
		this.amount = amount;
		this.cashContent = cashContent;
	}
	

	
}

	