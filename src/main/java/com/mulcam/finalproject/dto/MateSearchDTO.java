package com.mulcam.finalproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MateSearchDTO {
	private String category;
	private String state;
	private String area;
	private String query;

	private String categorySQL = "SELECT category";
	private String stateSQL = "SELECT state";
	private String areaSQL = "SELECT placeCode";
	private String querySQL = "";

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
