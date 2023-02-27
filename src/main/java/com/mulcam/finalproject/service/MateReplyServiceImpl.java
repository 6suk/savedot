package com.mulcam.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.MateReplyDAO;
import com.mulcam.finalproject.entity.MateReply;

@Service
public class MateReplyServiceImpl implements MateReplyService {
	
	@Autowired
	MateReplyDAO mateReplyDAO;

	@Override /* 댓글등록 */ 
	public void insertReply(MateReply reply) {
		MateReply reply2 = mateReplyDAO.getMaxGrp(reply);
		reply2.setGrp(reply2.getGrp() + 1); // (새글일경우) grp 최대값의 + 1 해준다. 
		mateReplyDAO.insertReply(reply);
	}

	@Override /* 댓글 - 댓글 입력 */
	public void insertReReply(MateReply reply) { 
		MateReply mr = mateReplyDAO.getReply(reply.getGrp()); // 같은 그룹번호를가져오기..
		reply.setGrpl(mr.getGrpl()+1);
		reply.setGrps(mr.getGrps()+1);
		mateReplyDAO.insertReReply(reply);
	}

	@Override
	public void deleteReply(Long rid) {
		mateReplyDAO.deleteReply(rid);
	}

	@Override /*댓글 목록 출력*/
	public List<MateReply> getReplies() {
		List<MateReply> list = mateReplyDAO.getReplies();
		return list;
	}

	@Override
	public void updateReply(MateReply mateReply) {
		mateReplyDAO.updateReply(mateReply);
	}


}
