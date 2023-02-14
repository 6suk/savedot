package com.mulcam.finalproject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.entity.Cash;
import com.mulcam.finalproject.entity.CashImg;

@Mapper
public interface CashDao{

		@Select("select * from cash where cid=#{cid}")
		public Cash getCash(int cid);


		/* 수입지출 등록 DB로 데이터 보내기 */
		@Insert("INSERT INTO cash VALUES(DEFAULT, #{category}, #{regDate}, #{amount}, #{content}, #{memo}, #{uid})")
		public void cashSave(Cash cash);

		/* 영수증 이미지 DB로 보내기 */
		@Insert("INSERT INTO ocr_img VALUES(DEFAULT,#{fileName},#{ext}, #{origFileName}, #{filePath}, #{saveDate}, #{cid})")
		public void ocrImgSave(CashImg cashImg);

}
