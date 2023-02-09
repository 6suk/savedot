package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.dto.MypageSumDTO;
import com.mulcam.finalproject.entity.CSuccess;

public interface CSuccessService {
	
	void insert (CSuccess cs);
	
	List<CSuccess> getList();
	
	MypageSumDTO getSum(String uid);
}
