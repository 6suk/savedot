package com.mulcam.finalproject.service;

import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.CashImg;

public interface CashService {

	Cash getCash(int cid);

	/* 수입지출 저장하기 */
	void cashSave(Cash cash);

	/* 이미지 local 저장하기 */
	void LocalSaveFiles(MultipartFile ocrImgs);

	/* ocr img DB에저장하기 */
	void ocrImgSave(CashImg cashImg);

	/* cash DB의 cid 구하기 --> img DB cid에 동일값을 넣어주기 위해서 */
	int getRecentCid(String uid);


}
