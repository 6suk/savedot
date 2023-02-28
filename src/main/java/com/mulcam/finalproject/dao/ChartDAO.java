package com.mulcam.finalproject.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChartDAO {
	/** HOME : 조각메이트 - 평균 세이브 금액 */
	@Select("SELECT AVG(price2 * positionNum) AS saveprice FROM mate;")
	public double homeMateSavePrice();
	
	/** HOME : 조각메이트 - 평균 세이브 금액 */
	@Select("SELECT AVG(((price2 * positionNum) / price1) * 100) AS savePercentage FROM mate;")
	public double homeMateSavePercentage();
	
	/** HOME : 조각메이트 - 거래가 가장 많이 올라오는 요일 */
	@Select("SELECT case DAYOFWEEK(regDate)"
			+ "	 when '1' then '일요일'"
			+ "	 when '2' then '월요일'"
			+ "	 when '3' then '화요일'"
			+ "	 when '4' then '수요일'"
			+ "	 when '5' then '목요일'"
			+ "	 when '6' then '금요일'"
			+ "	 when '7' then '토요일'"
			+ "	 end `dayofweek` FROM mate"
			+ " GROUP BY `dayofweek`"
			+ " ORDER BY COUNT(*) DESC"
			+ " LIMIT 1;")
	public String homeMateTopDayOfWeek();
	
	/** HOME : 조각메이트 - 만남 거래가 가장 활발한 지역 */
	@Select("SELECT a.name FROM mate m"
			+ " JOIN area_code a"
			+ " ON m.placeCode = a.id"
			+ " WHERE placeCode > 0"
			+ " GROUP BY placeCode"
			+ " ORDER BY COUNT(m.mid) DESC"
			+ " LIMIT 1;")
	public String homeMateTopArea();
	
	/** HOME : 챌린지 - 전체 유저 한달동안 아낀 비용 평균 */
	@Select("SELECT SUM(c.camount) / COUNT(DISTINCT s.uid) AS `avg` "
			+ "	FROM csuccess s"
			+ " JOIN challenge c"
			+ "	ON s.cid = c.cid"
			+ " WHERE date_format(s.sucDate, '%Y-%m') > date_format(DATE_SUB(NOW(), INTERVAL 1 MONTH),'%Y-%m')"
			+ " GROUP BY MONTH(s.sucDate);")
	public double homeChallengeOneMonthAllUser();

	/** HOME : 챌린지 - 전체 유저 하루동안 아낀 비용 평균 (NULL의 경우 전날 데이터 조회) */
	@Select("SELECT IFNULL(SUM(c.camount) / COUNT(DISTINCT s.uid),0) AS `avg`"
			+ " FROM csuccess s"
			+ " JOIN challenge c"
			+ " ON s.cid = c.cid"
			+ " WHERE DATE(s.sucDate) = CURDATE() - ${day};")
	public double homeChallengeTodayAllUser(int day);
	
	/** HOME : 챌린지 - 가장 참여율이 높은 나이대 */
	@Select("SELECT TRUNCATE(ROUND((TO_DAYS(NOW()) - (TO_DAYS(u.birthDate))) / 365) + 1, -1) AS age FROM csuccess s"
			+ " JOIN user u"
			+ " ON u.id = s.uid"
			+ " GROUP BY `age`"
			+ " ORDER BY COUNT(s.sid) DESC LIMIT 1;")
	public int homeChallengeTopAge();
	
	/** HOME : 챌린지 - 가장 많이 참여한 챌린지 */
	@Select("SELECT c.cname FROM csuccess s"
			+ " JOIN challenge c"
			+ " ON c.cid = s.cid"
			+ " GROUP BY c.cid"
			+ " ORDER BY COUNT(s.sid) DESC"
			+ " LIMIT 1;")
	public String homeChallengeTopType();
	
	
	/** Mypage/cash : 유저 - n개월간 지출 내역 */
	@Select("SELECT date_format(regDate,'%Y-%m') AS `date`,"
			+ "		SUM(amount) AS `sum` FROM cash"
			+ "	WHERE uid = #{uid}"
			+ "		AND category = 0"
			+ "	GROUP BY `date`"
			+ "		HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> cashMonthByUid(int month, String uid);
	
	/** Mypage/cash : 유저 - n개월간 현재 날짜까지 지출 내역 */
	@Select("SELECT date_format(regDate, '%Y-%m') AS `date`,"
			+ "	SUM(amount) AS `sum` FROM cash"
			+ "	WHERE date_format(regDate,'%d') >= '01'"
			+ "		AND date_format(regDate,'%d') <= date_format(CURDATE(),'%d')"
			+ "		AND category = 0"
			+ "		AND uid = #{uid}"
			+ "	GROUP BY `date`"
			+ "	HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> cashMonthDateByUid(int month, String uid);
	
	
	/** Mypage/cash : 전체 유저 - n개월간 현재 날짜까지 지출 내역(사용안함) */
	@Select("SELECT date_format(regDate, '%Y-%m') AS `date`,"
			+ "	SUM(amount) / COUNT(DISTINCT uid) AS `avg` FROM cash"
			+ "	WHERE date_format(regDate,'%d') >= '01'"
			+ "		AND date_format(regDate,'%d') <= date_format(CURDATE(),'%d')"
			+ "		AND category = 0"
			+ "	GROUP BY `date`"
			+ "	HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> cashMonthDateAllUser(int month);
	
	/** Mypage/cash : 전체 유저 - n개월간 지출 평균 (나이대별) */
	@Select("SELECT date_format(regDate,'%Y-%m') AS `date`,"
			+ "		SUM(amount) / COUNT(DISTINCT c.uid) AS `avg` FROM cash c"
			+ "	JOIN user u"
			+ "	ON c.uid = u.id"
			+ "	WHERE category = 0"
			+ "	AND TRUNCATE(ROUND((TO_DAYS(NOW()) - (TO_DAYS(u.birthDate))) / 365) + 1, -1)  = #{age}"
			+ "	GROUP BY `date`"
			+ "		HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${month} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> cashMonthAllGroupAge(int month, int age);
	
	/** Mypage/Challenge : 특정 유저 - n개월동안 아낀 비용 합계 Data1 */
	@Select("SELECT date_format(s.sucDate,'%Y-%m') AS `date`,"
			+ "	SUM(c.camount) AS `sum`"
			+ "	FROM csuccess s"
			+ " JOIN challenge c"
			+ "	ON s.cid = c.cid"
			+ " WHERE date_format(s.sucDate, '%Y-%m') > date_format(DATE_SUB(NOW(), INTERVAL ${months} MONTH),'%Y-%m')"
			+ " AND s.uid = #{uid}"
			+ " GROUP BY `date`"
			+ " ORDER BY `date`;")
	public List<Map<String, Object>> challengeMonthByUid(int months, String uid);
	
	/** Mypage/Challenge : 전체 유저(나이대) - n개월동안 아낀 비용 평균 Data3 */
	@Select("SELECT date_format(s.sucDate,'%Y-%m') AS `date`,"
			+ "	SUM(c.camount) / COUNT(DISTINCT s.uid) AS `avg`"
			+ "	FROM csuccess s"
			+ " 	JOIN challenge c"
			+ " 	JOIN user u"
			+ "	ON s.cid = c.cid AND s.uid = u.id"
			+ " WHERE date_format(s.sucDate, '%Y-%m') > date_format(DATE_SUB(NOW(), INTERVAL ${months} MONTH),'%Y-%m')"
			+ " AND TRUNCATE(ROUND((TO_DAYS(NOW()) - (TO_DAYS(u.birthDate))) / 365) + 1, -1)  = #{age}"
			+ " GROUP BY `date`"
			+ " ORDER BY `date`;")
	public List<Map<String, Object>> challengeMonthAllGroupAge(int months, int age);
	
	/** Mypage/Challenge : 유저 - n개월간 현재 날짜까지 아낀비용 합계 Data2*/
	@Select("SELECT date_format(s.sucDate,'%Y-%m') AS `date`,"
			+ "	SUM(c.camount) AS `sum`"
			+ "	FROM csuccess s"
			+ " 	JOIN challenge c"
			+ "	ON s.cid = c.cid"
			+ "	WHERE date_format(s.sucDate,'%d') >= '01'"
			+ "		AND date_format(s.sucDate,'%d') <= date_format(CURDATE(),'%d')"
			+ " 		AND s.uid = #{uid}"
			+ "	GROUP BY `date`"
			+ "	HAVING `date` > date_format(DATE_SUB(NOW(), INTERVAL ${months} MONTH),'%Y-%m')"
			+ "	ORDER BY `date`;")
	public List<Map<String, Object>> challengeMonthDateByUid(int months, String uid);

	
	/** Mypage/Challenge : 유저 - 참여한 챌린지(n개월) Data4 */
	@Select("SELECT c.cid, COUNT(s.sid) AS `sum`"
			+ "FROM challenge c"
			+ " LEFT JOIN csuccess s ON c.cid = s.cid"
			+ " 	AND s.uid = #{uid}"
			+ " 	AND s.sucDate > date_format(DATE_SUB(NOW(), INTERVAL ${months} MONTH),'%Y-%m')"
			+ " 	GROUP BY c.cid;")
	public List<Map<String, Object>> challengeTypeMonthByUid(int months, String uid);
	
	/** Mypage/Challenge : 전체 유저 - 참여한 챌린지(n개월 / 나이대) Data5 */
	@Select("SELECT c.cid, COUNT(s.sid) / COUNT(DISTINCT s.uid) AS `avg` FROM csuccess s"
			+ " 	JOIN challenge c"
			+ " 	JOIN user u"
			+ "	ON s.cid = c.cid AND s.uid = u.id"
			+ " WHERE s.sucDate > date_format(DATE_SUB(NOW(), INTERVAL ${months} MONTH),'%Y-%m')"
			+ " AND TRUNCATE(ROUND((TO_DAYS(NOW()) - (TO_DAYS(u.birthDate))) / 365) + 1, -1)  = #{age}"
			+ " GROUP BY c.cid;")
	public List<Map<String, Object>> challengeTypeMonthAllGroupAge(int months, int age);
}
