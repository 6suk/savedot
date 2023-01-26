package com.mulcam.finalproject.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface MateService {
	public void saveImgs(List<MultipartFile> imgs);
}
