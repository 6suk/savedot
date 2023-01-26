package com.mulcam.finalproject.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MateServiceImpl implements MateService {

	@Override
	public void saveImgs(List<MultipartFile> imgs) {
		UUID uuid = UUID.randomUUID();
		AtomicInteger indexHolder = new AtomicInteger();
		
		imgs.forEach(img -> {
			String newFileName = uuid.toString() + img.getOriginalFilename();
			try {
				img.transferTo(new File(newFileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			System.out.println(indexHolder.getAndIncrement()+1 + " : " + img.getOriginalFilename());
		});

	}

}
