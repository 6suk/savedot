package com.mulcam.finalproject.service;

import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.CashOcrImg;

public interface CashService {

	Cash getCash(int cid);
	void cashSave(Cash cash);
	void LocalSaveFiles(MultipartFile ocrImgs);
	void OcrImgSave(CashOcrImg ocrImg);


}
