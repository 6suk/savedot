package com.mulcam.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dao.MateDAO;
import com.mulcam.finalproject.dto.ImageDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.MateSearchDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.util.ImageUploadUtil;

@Service
public class MateServiceImpl implements MateService {
	@Autowired
	MateDAO mateDAO;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ImageUploadUtil imageUpload;

	@Value("${spring.servlet.multipart.location}")
	private String location;

	@Override
	public MateDTO findOneByMid(String id) {
		Long longId = Long.parseLong(id);
		return findOneByMid(longId);
	}

	@Override
	public MateDTO findOneByMid(Long id) {
		Mate mate = mateDAO.findOneByMid(id);
		if (mate == null) {
			return null;
		} else {
			MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
			return UserAndImgsMapping(mateDTO);
		}
	}

	@Override
	public Long save(MateDTO mateDTO) {
		Mate mate = modelMapper.map(mateDTO, Mate.class);
		mateDAO.save(mate);
		Long mid = mateDAO.findSaveId();

		if (mateDTO.getReqimgs() != null) {
			List<MultipartFile> files = mateDTO.getReqimgs();
			List<ImageDTO> imgInfoList = imageUpload.LocalSaveFiles(files);

			for (ImageDTO img : imgInfoList) {
				MateImg mateImg = img.setMateImgInfo(mid);
				mateDAO.ImgSave(mateImg); // DB저장
			}
		}
		return mid;
	}

	@Override
	public void updateAddApply(Long mid) {
		mateDAO.updateAddApply(mid);
	}

	@Override
	public void updateCancelApply(Long mid) {
		mateDAO.updateCancelApply(mid);
	}

	@Override
	public List<MateDTO> findAllBySearch(MateSearchDTO mateSearchDTO) {
		List<Mate> list = mateDAO.findAllBySearch(mateSearchDTO);
		List<MateDTO> mateDTOList = new ArrayList<>();

		list.forEach(mate -> {
			MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
			mateDTOList.add(UserAndImgsMapping(mateDTO));
		});
		return mateDTOList;
	}
	
	@Override
	public List<MateDTO> findAllByUid(MateSearchDTO mateSearchDTO){
		List<Mate> list = mateDAO.findAllByUid(mateSearchDTO);
		List<MateDTO> mateDTOList = new ArrayList<>();
		
		list.forEach(mate -> {
			MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
			mateDTOList.add(UserAndImgsMapping(mateDTO));
		});
		return mateDTOList;
	}
	
	@Override
	public List<MateDTO> findLikeByUid(MateSearchDTO mateSearchDTO){
		List<Mate> list = mateDAO.findLikeByUid(mateSearchDTO);
		List<MateDTO> mateDTOList = new ArrayList<>();
		
		list.forEach(mate -> {
			MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
			mateDTOList.add(UserAndImgsMapping(mateDTO));
		});
		return mateDTOList;
	}

	@Override
	public void update(MateDTO mateDTO) {
		Long mid = mateDTO.getMid();
		Mate mate = modelMapper.map(mateDTO, Mate.class);
		List<MateImg> mateImgList = mateDAO.findImgsByMid(mid); // 기존 이미지 정보 찾기

		if (mateImgList.size() > 0) {
			/** 기존 이미지 로컬 삭제 */
			for (MateImg img : mateImgList) {
				ImageDTO imgDTO = img.setImageDTO();
				ResponseEntity<Boolean> imgDeleteState = imageUpload.removeFile(imgDTO);
			}
			/** 기존 이미지 DB 삭제 */
			mateDAO.deleteImgsByMid(mid);
		}

		mateDAO.update(mate); // 게시물 DB 업데이트
		if (mateDTO.getReqimgs() != null) {
			List<MultipartFile> files = mateDTO.getReqimgs();
			List<ImageDTO> imgInfoList = imageUpload.LocalSaveFiles(files);

			for (ImageDTO img : imgInfoList) {
				MateImg mateImg = img.setMateImgInfo(mid);
				/** 새로운 이미지 DB 저장 */
				mateDAO.ImgSave(mateImg);
			}
		}
	}

	@Override
	public void delete(Long mid) {
		mateDAO.deleteMateByMid(mid);
	}

	/** 이미지 + 작성자 매핑 */
	public MateDTO UserAndImgsMapping(MateDTO mateDTO) {
		// 이미지 매핑
		mateDTO.setImgInfo(mateDAO.findImgsByMid(mateDTO.getMid()));
		// 작성자 매핑
		UserDTO userDTO = userService.findByUid(mateDTO.getUid());
		mateDTO.setUser(userDTO);
		return mateDTO;
	}

}
