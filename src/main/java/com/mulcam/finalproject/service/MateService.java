package com.mulcam.finalproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateImg;

public interface MateService {
	/** detail : 디테일 페이지 검색*/
	public Optional<Mate> findById(String id);
	
	/** detail : 디테일 페이지 검색*/
	public Optional<Mate> findById(Long id);
	
	/** write : 이미지 파일 로컬 저장 */
	public List<MateImg> LocalSaveFiles(List<MultipartFile> imgs);
	
	/** write : 게시글 DB 저장 */
	public Long save(Mate mate, List<MultipartFile> imgs);
}
