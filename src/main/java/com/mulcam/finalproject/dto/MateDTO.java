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
	private LocalDateTime modDate;
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

}
