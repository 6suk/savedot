package com.mulcam.finalproject.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import com.mulcam.finalproject.entity.CashImg;
import com.mulcam.finalproject.entity.MateImg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ImageDTO {
	private String inputFile;
	private UUID uuid;
	private String ext;
	private File path;

	public MateImg setMateImgInfo() {
		// Img Entity 생성
		MateImg mateImg = MateImg.builder()
				.origFileName(inputFile)
				.id(uuid).ext(ext)
				.filePath(path.toString())
				.saveDate(LocalDate.now())
				.build();
		return mateImg;

    }

	public CashImg setCashImgInfo() {
		// Img Entity 생성
		CashImg cashImg = CashImg.builder()
				.origFileName(inputFile)
				.fileName(uuid.toString()).ext(ext)
				.filePath(path.toString())
				.saveDate(LocalDate.now())
				.build();
		return cashImg;
	}
}
