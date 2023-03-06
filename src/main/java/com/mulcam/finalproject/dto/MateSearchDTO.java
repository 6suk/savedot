package com.mulcam.finalproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MateSearchDTO {
	private Long uid;
	private String category;
	private String state;
	private String area;
	private String query;
	// apply
	private String isApply;
	private String sendBy;

	private String categorySQL = "SELECT category";
	private String stateSQL = "SELECT state";
	private String areaSQL = "SELECT placeCode";
	private String querySQL = "";
	private String isApplySQL = "SELECT isApply";
	private String sendBySQL;


	public void setUid(Long uid) {
		this.uid = uid;
		if(this.sendBy != null) {
			switch (this.sendBy) {
			case "A":
				this.sendBySQL = "m.uid = " + this.uid + " or a.uid = " + this.uid;
				break;
			case "0":
				this.sendBySQL = "a.uid = " + this.uid;
				break;
			case "1":
				this.sendBySQL = "m.uid = " + this.uid;
				break;
			default:
				break;
			}
		}else {
			this.sendBySQL = "m.uid = " + this.uid + " or a.uid = " + this.uid;
		}
	}

	public void setIsApply(String isApply) {
		this.isApply = isApply;
		if (!isApply.equals("A")) {
			this.isApplySQL = isApply;
		}
	}

	public void setCategory(String category) {
		this.category = category;
		this.categorySQL = category.replace("-", ",");
	}

	public void setState(String state) {
		this.state = state;
		if (!state.equals("A")) {
			this.stateSQL = state;
		}
	}

	public void setArea(String area) {
		this.area = area;
		if (!area.equals("00")) {
			this.areaSQL = area.replace("-", ",");
		}
	}

	public void setQuery(String query) {
		this.query = query;
		this.querySQL = query;
	}

}
