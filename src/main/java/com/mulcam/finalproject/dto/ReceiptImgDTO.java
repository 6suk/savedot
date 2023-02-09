package com.mulcam.finalproject.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiptImgDTO {   /* controller */ 
	
	private MultipartFile receiptImgs;
	private LocalDateTime cashDate;
	private int amount;
	private String cashContent;
	private String memo;


}
