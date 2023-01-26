package com.mulcam.finalproject.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Mate {
	private String area;
	private String title;
	private String price1;
	private String price2;
	private String content;
	private int category; // [0/1]
	private String categoryName; // [조각메이트/OTT메이트]
	private List<MultipartFile> imgs;

	public Mate() {
	};

	public Mate(String area, String title, String price1, String price2, String content, int category,
			String categoryName, List<MultipartFile> imgs) {
		super();
		this.area = area;
		this.title = title;
		this.price1 = price1;
		this.price2 = price2;
		this.content = content;
		this.category = category;
		this.categoryName = categoryName;
		this.imgs = imgs;
	}

	public void setCategory(int category) {
		this.category = category;
		categoryName = category == 0 ? "조각메이트" : "OTT메이트";
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<MultipartFile> getImgs() {
		return imgs;
	}

	public void setImgs(List<MultipartFile> imgs) {
		this.imgs = imgs;
	}

	public int getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Mate [area=" + area + ", title=" + title + ", price1=" + price1 + ", price2=" + price2 + ", content="
				+ content + ", category=" + category + ", categoryName=" + categoryName + ", imgs=" + imgs + "]";
	}
	
	

}
