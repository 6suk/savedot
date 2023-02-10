package com.mulcam.finalproject.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

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
public class CashImg {
	
	private int iid;
	private String fileName;
	private String ext;
	private String origFileName;
	private String filePath;
	private LocalDate saveDate;
	private int cid;
	
}
