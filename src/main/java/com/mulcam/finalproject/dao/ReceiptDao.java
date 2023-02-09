package com.mulcam.finalproject.dao;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.dto.ReceiptImgDTO;
import com.mulcam.finalproject.entity.Receipt;

@Mapper
public interface ReceiptDao {
	
		@Select("select * from cash where cashRid=#{cid}")
		public Receipt getReceiptVO(int cashRid);
		
		@Insert("INSERT INTO cash VALUES(DEFAULT, #{category}, #{regDate}, #{amount}, #{content}, #{memo}, #{uid})")
		public void receiptSave(Receipt receiptVO);

//		// img insert dao 필요 
//		@Insert("INSERT INTO receiptimg VALUES(DEFAULT,#{recieptExt}, #{receiptFilePath}, #{OriginalFileName}, #{saveFileName},#{receiptSaveDate},#{receiptSaveDate},#{uid},#{cashRid}")
//		public void receiptImgSave(OcrImgVO receiptImg);


}
