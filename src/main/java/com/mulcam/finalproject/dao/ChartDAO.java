package com.mulcam.finalproject.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChartDAO {
	/** 유저 - n개월간 지출 내역 */
	@Select("SELECT date_format(regDate,'%Y-%m') AS `date`,"
			+ "		SUM(amount) AS `sum` FROM cash"
			+ "	WHERE uid = #{uid}"
			+ "		AND category = 0"
			+ "	GROUP BY `date`"
			+ "		HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> findCashMonthSumByUid(int month, String uid);
	
	/** 유저 - n개월간 현재 날짜까지 지출 내역 */
	@Select("SELECT date_format(regDate, '%Y-%m') AS `date`,"
			+ "	SUM(amount) AS `sum` FROM cash"
			+ "	WHERE date_format(regDate,'%d') >= '01'"
			+ "		AND date_format(regDate,'%d') <= date_format(CURDATE(),'%d')"
			+ "		AND category = 0"
			+ "		AND uid = #{uid}"
			+ "	GROUP BY `date`"
			+ "	HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> findCashDateSumByUid(int month, String uid);
	
	
	/** 전체 유저 - n개월간 현재 날짜까지 지출 내역(사용안함) */
	@Select("SELECT date_format(regDate, '%Y-%m') AS `date`,"
			+ "	SUM(amount) / COUNT(DISTINCT uid) AS `avg` FROM cash"
			+ "	WHERE date_format(regDate,'%d') >= '01'"
			+ "		AND date_format(regDate,'%d') <= date_format(CURDATE(),'%d')"
			+ "		AND category = 0"
			+ "	GROUP BY `date`"
			+ "	HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> findCashDateSumAll(int month);
	
	@Select("SELECT date_format(regDate,'%Y-%m') AS `date`,"
			+ "		SUM(amount) / COUNT(DISTINCT c.uid) AS `avg` FROM cash c"
			+ "	JOIN user u"
			+ "	ON c.uid = u.id"
			+ "	WHERE category = 0"
			+ "	AND TRUNCATE(ROUND((TO_DAYS(NOW()) - (TO_DAYS(u.birthDate))) / 365) + 1, -1)  = #{age}"
			+ "	GROUP BY `date`"
			+ "		HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> findCashMonthAvgAllByAgeGroup(int month, int age);
	
	
}
