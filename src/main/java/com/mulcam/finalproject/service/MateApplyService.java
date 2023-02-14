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

	/** Apply : Uid 검색 */
	public List<MateApplyDTO> findByUid(User user);
	
	/** Apply : Uid 검색 */
	public List<MateApplyDTO> findByUid(Long uid);

	/** Apply : Mid 검색 */
	public List<MateApplyDTO> findByMid(Mate mate);

}
