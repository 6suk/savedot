package com.mulcam.finalproject.dao;

import java.util.List;

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

		/* cid 값 가져오기 */
		@Select("SELECT cid FROM cash"
				+ " WHERE uid=#{uid} ORDER BY cid DESC LIMIT 1")
		public int getRecentCid(String uid);

		/* 영수증 이미지 DB로 보내기 */
		@Insert("INSERT INTO ocr_img VALUES(DEFAULT,#{fileName},#{ext}, #{origFileName}, #{filePath}, #{saveDate}, #{cid})")
		public void ocrImgSave(CashImg cashImg);

		/* 해당 기간별 수입&지출 리스트 */
		@Select("SELECT * FROM cash"
				+ " WHERE uid=#{uid} AND date(regDate) between #{startDate} AND #{endDate}")
		List<Cash> getList(String uid, String startDate, String endDate);

		/* 오늘 '지출'합계 구하기 */
		@Select("SELECT SUM(amount) FROM cash"
				+ " WHERE uid=#{uid} AND category=0 AND regDate=DATE(NOW())")
		public int sumNowExpense(String uid);

		/* 오늘 '수입'합계 구하기 */
		@Select("SELECT SUM(amount) FROM cash"
				+ " WHERE uid=#{uid} AND category=1 AND regDate=DATE(NOW())")
		public int sumNowIncome(String uid);

		/* 수입/지출 전체 리스트 가져오기 (ocr_img DB JOIN) */
	//	@Select("SELECT category,content,amount,memo FROM cash WHERE uid=#{uid} ORDER BY regDate DESC")
		@Select("SELECT a.category, a.content,a.amount,a.memo, a.regDate, b.filePath, b.fileName, b.ext, b.saveDate"
				+ " FROM cash AS a"
				+ " LEFT JOIN ocr_img AS b"
				+ " ON a.cid = b.cid"
				+ " WHERE uid=#{uid}"
				+ " ORDER BY a.regDate DESC")
		List<Cash> getAllCashList(String uid);  // 한달동안 list 뽑기 -> 날짜기준 -> 목록출력

}
