package com.mulcam.finalproject.entity;

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
public class News {
	
	private String title;
	private String link;
	private String description;
	private String pubDate;
	private String category;

}