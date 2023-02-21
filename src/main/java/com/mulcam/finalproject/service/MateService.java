package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.MateSearchDTO;

public interface MateService {
	/** detail : 디테일 페이지 검색 */
	public MateDTO findOneByMid(String id);

	/** detail : 디테일 페이지 검색 */
	public MateDTO findOneByMid(Long id);

	/** write : 게시글 DB 저장 */

	public Long save(MateDTO mateDTO);
	
	/** Apply : mate position apply num Update */
	public void updateAddApply(Long mid);
	
	/** Apply : mate position apply num Cancle */
	public void updateCancelApply(Long mid);
	
	/** LIST : 검색 결과 조회 */
	public List<MateDTO> findAllBySearch(MateSearchDTO mateSearchDTO);

}
