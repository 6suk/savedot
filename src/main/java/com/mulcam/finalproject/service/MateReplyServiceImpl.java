package com.mulcam.finalproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		int maxGrp = 0;
		try {
			maxGrp = mateReplyDAO.getMaxGrp(reply.getMid());
		} catch (Exception e) {
		}
		reply.setGrp(maxGrp + 1); // (새글일경우) grp 최대값의 + 1 해준다.
		mateReplyDAO.insertReply(reply);
	}

	@Override /* 댓글 - 댓글 입력 */
	public void insertReReply(MateReply reply) {
		// 예림 수정
		List<MateReply> mr = mateReplyDAO.getReply(reply.getGrp(), reply.getMid()); // 같은 그룹번호를가져오기..
		reply.setGrps(mr.get(mr.size() - 1).getGrps() + 1);
		mateReplyDAO.insertReReply(reply);
	}

	@Override
	public MateReply getGrp(long rid, int grp) {
		MateReply mry = mateReplyDAO.getGrp(rid, grp);
		return mry;
	}

	/* 댓글 삭제 */
	@Override
	public void deleteReply(Long rid) {
		mateReplyDAO.deleteReply(rid);
	}

	@Override /* 댓글 목록 출력 */
	public List<MateReply> getReplies(long mid) {
		List<MateReply> list = mateReplyDAO.getReplies(mid);
		List<MateReply> grpInfo_ = mateReplyDAO.findGrpAndGrpsMaxByMid(mid);
		Map<Integer, Integer> grpInfo = new HashMap<>();
		grpInfo_.forEach(x -> grpInfo.put(x.getGrp(), x.getMaxGrps()));
		
		
		list.forEach(x -> {
			int maxGrpsNum = (int)grpInfo.get(x.getGrp());
			x.setMaxGrps(maxGrpsNum);
		});

		return list;
	}

	/* 댓글 수정 */
	@Override
	public void updateReply(MateReply mateReply) {
		mateReplyDAO.updateReply(mateReply);
	}

	@Override
	public MateReply getMateReply(long rid) {
		MateReply mr = mateReplyDAO.getMateReply(rid);
		return mr;
	}
	
	/* 마지막 rid 가져오기 */
	public Long findRid() {
		return mateReplyDAO.findRid();
	}

	@Override
	public void plusReply(long mid) {
		mateReplyDAO.plusReply(mid);
		
	}

	@Override
	public void delReply(long mid) {
		mateReplyDAO.delReply(mid);
		
	}

}
