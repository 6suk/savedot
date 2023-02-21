package com.mulcam.finalproject.entity;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.taglibs.standard.extra.spath.Path;

import com.mulcam.finalproject.dto.ImageDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
@ToString
public class MateImg {
	private String id;

	private Long mid;
	
	private String ext;

	private String origFileName;

	private String filePath;

	private LocalDate saveDate;
	
	public ImageDTO setImageDTO() {
		ImageDTO imgDTO = ImageDTO.builder()
				.inputFile(this.origFileName)
				.uuid(UUID.fromString(id))
				.ext(this.ext)
				.path(new File(this.filePath))
				.saveDate(this.saveDate)
				.build();
		return imgDTO;
	}
	
}
