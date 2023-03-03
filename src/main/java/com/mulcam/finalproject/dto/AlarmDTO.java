package com.mulcam.finalproject.dto;

import java.time.LocalDateTime;

import com.mulcam.finalproject.entity.MateReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmDTO {
	private Long id;
	private int type; // 0:댓글알림 / 1:apply알림
	private Long toUid; // 받는사람
	private Long fromUid; // 보낸사람
	private Long rid;
	private Long mid;
	private Long aid;
	private LocalDateTime alarmDate;
	private int read;

	private MateDTO mate;
	private UserDTO user; // 보낸사람
	private MateReply reply;
	private MateApplyDTO apply;

	/** 매칭 신청 시 : 글 작성자에게 알람 */
	public void setApplyAlarm(MateApplyDTO apply) {
		this.apply = apply;
		this.mate = apply.getMate();
		this.type = 2;

		this.toUid = mate.getUid();	// 작성자
		this.fromUid = apply.getUid();	// 매칭 신청자
		this.mid = mate.getMid();
		this.aid = apply.getAid();
	}
	
	/** 매칭 상태 변경 시 : 신청자에게 알람 */
	public void setApplyStateAlarm(MateApplyDTO apply) {
		this.apply = apply;
		this.user = apply.getUser();
		this.mate = apply.getMate();
		this.type = 3;
		
		this.toUid = user.getUid();	// 신청자
		this.fromUid = mate.getUid();	// 작성자
		this.mid = mate.getMid();
		this.aid = apply.getAid();
	}

	/** 원 댓글 작성 시 : 글 작성자에게 알람 */
	public void setMateAlarm(MateDTO mate, MateReply reply) {
		this.mate = mate;
		this.reply = reply;
		this.type = 0;

		this.toUid = mate.getUid(); // 작성자
		this.fromUid = reply.getUid(); // 댓글 작성자
		this.rid = reply.getRid();
		this.mid = mate.getMid();
	}

	/** 대댓글 작성 시 : 해당 그룹에 작성한 모두에게 알람(댓글 작성자 제외) */
	public void setReplyAlarm(MateDTO mate, MateReply reply) {
		this.mate = mate;
		this.reply = reply;
		this.type = 1;

		this.fromUid = reply.getUid();
		this.rid = reply.getRid();
		this.mid = mate.getMid();
	}

}
