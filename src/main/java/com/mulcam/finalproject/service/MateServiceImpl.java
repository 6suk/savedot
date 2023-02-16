package com.mulcam.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dto.ImageDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.repository.MateImgRepository;
import com.mulcam.finalproject.repository.MateRepository;
import com.mulcam.finalproject.util.ImageUpload;

@Service
public class MateServiceImpl implements MateService {

	@Autowired
	MateRepository mateRepository;

	@Autowired
	MateImgRepository mateImgRepository;

	@Autowired
	ImageUpload imageUpload;

	@Value("${spring.servlet.multipart.location}")
	private String location;

	@Override
	public Optional<Mate> findById(String id) {
		Long longId = Long.parseLong(id);
		return mateRepository.findById(longId);
	}

	@Override
	public Optional<Mate> findById(Long id) {
		return mateRepository.findById(id);
	}

	@Override
	public Long save(Mate mate, List<MultipartFile> imgs) {
		List<ImageDTO> imgInfoList = imageUpload.LocalSaveFiles(imgs);
		List<MateImg> mateImgInfoList = new ArrayList<>();
		for(ImageDTO img : imgInfoList) {
			mateImgInfoList.add(img.setMateImgInfo());
		}

		mate.addImgList(mateImgInfoList);
		mate = mateRepository.save(mate);
		return mate.getId();
	}

	@Override
	public Long save(Mate mate) {
		mate = mateRepository.save(mate);
		return mate.getId();
	}


}
