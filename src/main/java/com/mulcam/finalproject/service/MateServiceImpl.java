package com.mulcam.finalproject.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.repository.MateImgRepository;
import com.mulcam.finalproject.repository.MateRepository;

@Service
public class MateServiceImpl implements MateService {

	@Autowired
	MateRepository mateRepository;
	@Autowired
	MateImgRepository mateImgRepository;
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
	public void save(Mate mate, List<MultipartFile> imgs) {
		List<MateImg> fileinfo = LocalSaveFiles(imgs);
		mate.addImgList(fileinfo);
		mateRepository.save(mate);
	}

	@Override
	public List<MateImg> LocalSaveFiles(List<MultipartFile> imgs) {
		File path = getPath();
		List<MateImg> mateimgList = new ArrayList<>();

		imgs.forEach(img -> {
			String inputFile = img.getOriginalFilename();
			String ext = "." + inputFile.substring(inputFile.lastIndexOf(".") + 1);
			UUID uuid = Generators.timeBasedGenerator().generate();
			String newfileName = uuid + ext;

			try {
				if (!path.exists())
					path.mkdir(); // 폴더가 없으면 폴더 생성
				img.transferTo(new File(path, newfileName));

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			// Img Entity 생성
			MateImg mateImg = MateImg.builder()
					.origFileName(inputFile)
					.id(uuid).ext(ext)
					.filePath(path.toString())
					.saveDate(LocalDate.now())
					.build();

			mateimgList.add(mateImg);
		});

		return mateimgList;
	}

	// 날짜별 폴더 생성
	public File getPath() {
		LocalDate localdate = LocalDate.now();
		File path = new File(location + File.separator + localdate.toString());
		return path;
	}

}
