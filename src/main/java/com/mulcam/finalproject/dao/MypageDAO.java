package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.dto.CalendarDTO;
import com.mulcam.finalproject.entity.CSuccess;
import com.mulcam.finalproject.entity.Cash;

@Mapper
public interface MypageDAO {
	
	@Select("SELECT CAST(sucDate AS DATE) AS date,"
			+ "	COUNT(*) AS stamp, uid"
			+ " FROM csuccess"
			+ "	 WHERE uid = #{uid}"
			+ "	 AND DATE_FORMAT(sucDate,'%Y-%m-%d') >= #{start}"
			+ "	 AND DATE_FORMAT(sucDate,'%Y-%m-%d') <= #{end}"
			+ " GROUP BY date")
	public List<CSuccess> getStampByUid(CalendarDTO calendarDTO);
	
	@Select("SELECT regDate, category, SUM(amount) amount, uid"
			+ "	FROM cash"
			+ "	WHERE regDate BETWEEN #{start} AND #{end}"
			+ "		AND uid = #{uid}"
			+ "	GROUP BY category, regDate;")
	public List<Cash> getCashByUid(CalendarDTO calendarDTO);
	
}
