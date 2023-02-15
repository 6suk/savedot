package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.dto.MateApplyDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateApply;
import com.mulcam.finalproject.entity.User;

public interface MateApplyService {

	/** Apply : 메이트 신청 DB 저장 */
	public Long save(MateApply mateApply);

	/** Apply : 메이트 신청 취소 */
	public void delete(Long aid);

	/** Send Apply : Uid 검색 (내가 신청한) */
	public List<MateApplyDTO> findBySendUid(User user);
	
	/** Send Apply : Uid 검색 (내가 신청한) */
	public List<MateApplyDTO> findBySendUid(Long uid);

	/** Get Apply : Uid 검색 (내가 작성한 게시물의 신청 리스트) */
	public List<MateApplyDTO> findByGetUid(User user);
	
	/** Get Apply : Uid 검색 (내가 작성한 게시물의 신청 리스트) */
	public List<MateApplyDTO> findByGetUid(Long uid);
	
	/** Apply : Mid 검색 */
	public List<MateApplyDTO> findByMid(Mate mate);
	
	
	

}
