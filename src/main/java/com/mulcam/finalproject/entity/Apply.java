package com.mulcam.finalproject.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Apply {
	
	@Id
	@Column(name = "aid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne // N:1
	@JoinColumn(name = "uid") // FK
	@NotNull
	private User user;	// 작성자
	
	@ManyToOne
	@JoinColumn(name = "mid")
	@NotNull
	private Mate mate;	// 게시물 작성자 정보는 여기에 있음
	
	@Column(length = 30)
	@NotNull
	private String area;
	
	@Column(length = 128)
	@NotNull
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@CreationTimestamp
	private LocalDateTime modDate;
	
	@NotNull
	@ColumnDefault("0")
	private int isDel;
	
	@NotNull
	@ColumnDefault("0")
	private int isApply;

}
