package com.mulcam.finalproject.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.mulcam.finalproject.dao.CashDao;
import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.CashImg;

@Service
public class CashServiceImpl implements CashService {

	@Value("${spring.servlet.multipart.location}")
	private String location;

	@Autowired
	private CashDao cashDao;
	
	public void updateCash(Cash cash) {
		cashDao.updateCash(cash);
	}
	
	public void deleteCash(int cid) {
		cashDao.deleteCash(cid);
	}

	@Override
	public Cash getCash(int cid) {
		Cash cash = cashDao.getCash(cid);
		return cash;
	}

	@Override // 영수증 인식 x , 직접 기입시
	public void cashSave(Cash cash) {
		cashDao.cashSave(cash);
	}


	@Override // 영수증 이미지 로컬 저장
	public void LocalSaveFiles(MultipartFile ocrImgs) {
		File path = getPath();

		String inputFile = ocrImgs.getOriginalFilename();
		String ext = "." + inputFile.substring(inputFile.lastIndexOf(".") + 1);
		UUID uuid = Generators.timeBasedGenerator().generate();
		String newfileName = uuid + ext;

		try {
			if (!path.exists())
				path.mkdir(); // 폴더가 없으면 폴더 생성
			ocrImgs.transferTo(new File(path, newfileName));

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

	@Override // 영수증 이미지 db저장
	public void ocrImgSave(CashImg cashImg) {
		cashDao.ocrImgSave(cashImg);
	}

	@Override
	public int getRecentCid(String uid) {
		int cid = cashDao.getRecentCid(uid);
		return cid;
	}



}