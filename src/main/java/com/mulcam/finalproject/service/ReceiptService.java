package com.mulcam.finalproject.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.mulcam.finalproject.entity.Receipt;

public interface ReceiptService {

	Receipt getReceiptVO(int cashRid);
	void receiptSave(Receipt receiptVO);
	void LocalSaveFiles(MultipartFile receiptImgs);

}
