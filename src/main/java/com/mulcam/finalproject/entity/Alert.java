package com.mulcam.finalproject.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
	private Long id;
	private int type; // 0:댓글알림 / 1:apply알림
	private Long toUid;	// 받는사람
	private Long fromUid;	// 보낸사람
	private Long rid;
	private Long mid;
	private Long aid;
	private LocalDateTime alertDate;
	private int read;
}
