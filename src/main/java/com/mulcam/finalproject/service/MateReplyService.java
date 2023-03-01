package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.entity.MateReply;

public interface MateReplyService {
	
	
	/* 댓글 목록 */
	public List<MateReply> getReplies(long mid); 
	
	/* 댓글 입력 */
	void insertReply(MateReply reply);
	
	/* 댓글 - 댓글 입력 */
	void insertReReply(MateReply reply);
	MateReply getGrp(long rid, int grp);
	
	/* 댓글 삭제 */
	void deleteReply(Long rid);
	
	/* 댓글 수정 */
	void updateReply(MateReply mateReply);
	MateReply getMateReply(long rid);
	
	/* 마지막 rid 가져오기 */
	public Long findRid();

}
