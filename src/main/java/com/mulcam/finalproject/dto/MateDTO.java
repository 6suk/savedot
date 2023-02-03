package com.mulcam.finalproject.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.entity.User;

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
public class MateDTO {
	private Long id;
	private User user;
	private int category;
	private String categoryName;
	private String area;
	private String title;
	private String content;
	private int price1;
	private int price2;
	private int savePrice;
	private LocalDateTime modDate;
	private String placeName;
	private String placeCoords;
	private String placeAddr;
	private int isDel;
	private int viewCnt;
	private int replyCnt;
	private int likeCnt;
	private List<MultipartFile> reqimgs;
	private List<MateImg> imgInfo; // View 전송용

	public void setCategory(int category) {
		String[] categoryNames = { "조각 메이트", "OTT 메이트" };
		this.category = category;
		categoryName = categoryNames[category];
	}
	
	public void setPrice1(int price1) {
		this.price1 = price1;
		if(this.price2 != 0) {
			this.savePrice = price1 - price2;
		}
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
		if(this.price1 != 0) {
			this.savePrice = price1 - price2;
		}
	}
	
	public void setSavePrice(int savePrice) {
			this.savePrice = price1 - price2;
	}


}
