package com.mulcam.finalproject.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.mulcam.finalproject.entity.MateImg;

@Service
public class MateServiceImpl implements MateService {

	@Value("${spring.servlet.multipart.location}")
	private String location;

	@Override
	public void saveImgs(List<MultipartFile> imgs) {
		File path = getPath();
		List<MateImg> mateimgList = new ArrayList<>();

		imgs.forEach(img -> {
			String inputFile = img.getOriginalFilename();
			String ext = "." + inputFile.substring(inputFile.lastIndexOf(".") + 1);
			String uuid = Generators.timeBasedGenerator().generate().toString();
			String newfileName = uuid + ext;

			try {
				if (!path.exists()) path.mkdir(); // 폴더가 없으면 폴더 생성
				img.transferTo(new File(path, newfileName));

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			// Img Entity 생성
			MateImg mateImg = new MateImg();
			mateImg.setOrigFileName(inputFile);
			mateImg.setImgId(uuid);
			mateImg.setExt(ext);
			mateImg.setFilePath(path.toString());
			mateimgList.add(mateImg);
		});
		
		mateimgList.forEach(x -> System.out.println(x));
	}

	// 날짜별 폴더 생성
	public File getPath() {
		LocalDate localdate = LocalDate.now();
		File path = new File(location + File.separator + localdate.toString());
		return path;
	}

}
