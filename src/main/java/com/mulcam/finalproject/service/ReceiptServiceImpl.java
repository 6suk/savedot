package com.mulcam.finalproject.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.tools.JavaFileManager.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.mulcam.finalproject.dao.ReceiptDao;
import com.mulcam.finalproject.entity.MateImg;
import com.mulcam.finalproject.entity.Receipt;

@Service
public class ReceiptServiceImpl implements ReceiptService {
	
	@Value("${spring.servlet.multipart.location}")
	private String location;

	@Autowired
	private ReceiptDao receiptDao;

	@Override
	public Receipt getReceiptVO(int cashRid) {
		Receipt receipt = receiptDao.getReceiptVO(cashRid);
		return receipt;
	}

	@Override // 영수증 인식 x , 직접 기입시
	public void receiptSave(Receipt receiptVO) {
		receiptDao.receiptSave(receiptVO);
	}


	@Override // 영수증 이미지 로컬 저장 
	public void LocalSaveFiles(MultipartFile receiptImgs) {
		File path = getPath();

		String inputFile = receiptImgs.getOriginalFilename();
		String ext = "." + inputFile.substring(inputFile.lastIndexOf(".") + 1);
		UUID uuid = Generators.timeBasedGenerator().generate();
		String newfileName = uuid + ext;

		try {
			if (!path.exists())
				path.mkdir(); // 폴더가 없으면 폴더 생성
			receiptImgs.transferTo(new File(path, newfileName));

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}

	// 날짜별 폴더 생성
	public File getPath() {
		LocalDate localdate = LocalDate.now();
		File path = new File(location + File.separator + localdate.toString());
		return path;
	}



}