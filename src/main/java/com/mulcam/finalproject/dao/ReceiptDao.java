package com.mulcam.finalproject.dao;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.dto.ReceiptImgDTO;
import com.mulcam.finalproject.entity.Receipt;

@Mapper
public interface ReceiptDao {
	
		@Select("select * from cashregister where cashRid=#{cashRid}")
		public Receipt getReceiptVO(int cashRid);
		
		@Insert("INSERT INTO cashregister VALUES(DEFAULT, #{category}, #{cashDate}, #{amount}, #{cashContent}, #{memo}, #{uid})")
		public void receiptSave(Receipt receiptVO);

//		// img insert dao 필요 
//		@Insert("INSERT INTO receiptimg VALUES(DEFAULT,#{recieptExt}, #{receiptFilePath}, #{OriginalFileName}, #{saveFileName},#{receiptSaveDate},#{receiptSaveDate},#{uid},#{cashRid}")
//		public void receiptImgSave(OcrImgVO receiptImg);


}
