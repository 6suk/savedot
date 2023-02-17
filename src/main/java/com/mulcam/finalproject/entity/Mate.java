package com.mulcam.finalproject.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class Mate {

	@Id
	@Column(name = "mid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne // N:1
	@JoinColumn(name = "uid") // FK
	@NotNull
	private User user;

	@NotNull
	@ColumnDefault("0")
	private int category;

	@Column(length = 20)
	private String bank;

	@Column(length = 50)
	private String accountNumber;

	private int telType;
	private String telUrl;

	@Column(length = 128)
	@NotNull
	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	@NotNull
	private int price1;

	@NotNull
	private int price2;

	@NotNull
	private int positionNum;
	
	@NotNull
	private int positonApplyNum;

	@Column(length = 128)
	private String openChat;

	@ColumnDefault("0")
	private int tradeType;

	@Column(length = 50)
	private String placeName;

	@Column(length = 40)
	private String placeCoords;

	private String placeAddr;

	@ColumnDefault("0")
	private int parcelType;

	private int parcelPrice;

	@CreationTimestamp
	private LocalDateTime modDate;

	@NotNull
	@ColumnDefault("0")
	private int isDel;

	@NotNull
	@ColumnDefault("0")
	private int viewCnt;

	@NotNull
	@ColumnDefault("0")
	private int replyCnt;

	@NotNull
	@ColumnDefault("0")
	private int likeCnt;

	@OneToMany(mappedBy = "mate", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MateImg> imgInfo;

	public void addImgList(List<MateImg> mateImgs) {
		mateImgs.forEach(i -> i.setMate(this));
		this.imgInfo = mateImgs;
	}

}
