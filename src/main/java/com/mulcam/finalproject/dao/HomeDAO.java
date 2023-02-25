package com.mulcam.finalproject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeDAO {
	
	/** 조각메이트 - 원가 기준 평균 세이브 금액 */
	@Select("SELECT AVG(price2 * positionNum) AS saveprice FROM mate;")
	public double findOneMateSavePrice();
	
	/** 조각메이트 - 거래가 가장 많이 올라오는 요일 */
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
	public String findOneMateTopUploadDayOfWeek();
	
	/** 조각메이트 - 만남 거래가 가장 활발한 지역 */
	@Select("SELECT a.name FROM mate m"
			+ " JOIN area_code a"
			+ " ON m.placeCode = a.id"
			+ " WHERE placeCode > 0"
			+ " GROUP BY placeCode"
			+ " ORDER BY COUNT(m.mid) DESC"
			+ " LIMIT 1;")
	public String findOneMateTopArea();
	
	/** 챌린지 - 전체 유저 x개월동안 아낀 비용 평균 */
	@Select("SELECT YEAR(s.sucDate) AS `year`,MONTH(s.sucDate) AS `month`,"
			+ "	SUM(c.camount) / COUNT(DISTINCT s.uid) AS `avg` "
			+ "	FROM csuccess s"
			+ " JOIN challenge c"
			+ "	ON s.cid = c.cid"
			+ " WHERE date_format(s.sucDate, '%Y-%m') > date_format(DATE_SUB(NOW(), INTERVAL ${months} MONTH),'%Y-%m')"
			+ " GROUP BY `month`"
			+ " ORDER BY `year`, `month`;")
	public Long findAllChallengeByMonth(int months);
	
	/** 챌린지 - 전체 유저 한달동안 아낀 비용 평균 */
	@Select("SELECT SUM(c.camount) / COUNT(DISTINCT s.uid) AS `avg` "
			+ "	FROM csuccess s"
			+ " JOIN challenge c"
			+ "	ON s.cid = c.cid"
			+ " WHERE date_format(s.sucDate, '%Y-%m') > date_format(DATE_SUB(NOW(), INTERVAL 1 MONTH),'%Y-%m')"
			+ " GROUP BY MONTH(s.sucDate);")
	public double findOneChallengeByMonth();

	/** 챌린지 - 전체 유저 하루동안 아낀 비용 평균 (NULL의 경우 전날 데이터 조회) */
	@Select("SELECT IFNULL(SUM(c.camount) / COUNT(DISTINCT s.uid),0) AS `avg`"
			+ " FROM csuccess s"
			+ " JOIN challenge c"
			+ " ON s.cid = c.cid"
			+ " WHERE DATE(s.sucDate) = CURDATE() - ${day};")
	public double findOneChallengeByToday(int day);
	
	/** 챌린지 - 가장 참여율이 높은 나이대 */
	@Select("SELECT TRUNCATE(ROUND((TO_DAYS(NOW()) - (TO_DAYS(u.birthDate))) / 365) + 1, -1) AS age FROM csuccess s"
			+ " JOIN user u"
			+ " ON u.id = s.uid"
			+ " GROUP BY `age`"
			+ " ORDER BY COUNT(s.sid) DESC LIMIT 1;")
	public int findOneChallengeByAgeGroup();
	
	/** 챌린지 - 가장 많이 참여한 챌린지 */
	@Select("SELECT c.cname FROM csuccess s"
			+ " JOIN challenge c"
			+ " ON c.cid = s.cid"
			+ " GROUP BY c.cid"
			+ " ORDER BY COUNT(s.sid) DESC"
			+ " LIMIT 1;")
	public String findOneChallengeByType();
	
	
	
}
