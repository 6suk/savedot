package com.mulcam.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dao.MateDAO;
import com.mulcam.finalproject.dao.UserDAO;
import com.mulcam.finalproject.dto.ImageDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.MateSearchDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.entity.User;
import com.mulcam.finalproject.util.ImageUpload;

@Service
public class MateServiceImpl implements MateService {
	@Autowired
	MateDAO mateDAO;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ImageUpload imageUpload;

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
		MateDTO mateDTO = modelMapper.map(mate, MateDTO.class);
		return UserAndImgsMapping(mateDTO);
	}

	@Override
	public Long save(MateDTO mateDTO) {
		Mate mate = modelMapper.map(mateDTO, Mate.class);
		mateDAO.save(mate);
		Long mid =  mateDAO.findSaveId();
		
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
