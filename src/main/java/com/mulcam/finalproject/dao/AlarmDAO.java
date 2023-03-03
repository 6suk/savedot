package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.entity.Alarm;
import com.mulcam.finalproject.entity.MateReply;

@Mapper
public interface AlarmDAO {
	
	@Insert("INSERT INTO alarm"
			+ "	(id, `type`, toUid, fromUid, rid, `mid`, aid, alarmDate, `read`)"
			+ "	VALUES (DEFAULT, #{type}, #{toUid}, #{fromUid}, #{rid}, #{mid}, #{aid}, DEFAULT, DEFAULT)")
	public void save(Alarm alarm);
	
	/** 그룹별 작성자 uid 가져오기 (알림용)*/
	@Select("SELECT DISTINCT(uid) FROM mate_reply"
			+ " WHERE `mid` = #{mid}"
			+ " AND grp = #{grp}"
			+ " AND uid != #{uid}")
	public List<Long> findGrpUidByMid(MateReply mateReply);
	
	/** apply 신청 취소 시 알림 삭제 */
	@Delete("DELETE FROM alarm WHERE aid = #{aid};")
	public void deleteAlarmByAid(Long aid);
	
	@Select("SELECT * FROM alarm"
			+ " WHERE toUid = #{uid}"
			+ " ORDER BY alarmDate DESC")
	public List<Alarm> findAlarmsByUid(Long uid);
	
	@Update("UPDATE alarm SET `read`= 1"
			+ "	WHERE id = #{id}"
			+ "	AND `read` = 0")
	public void editType(Alarm alarm);
	
	@Select("SELECT COUNT(*)"
			+ "	FROM alarm"
			+ "	WHERE toUid = #{uid}"
			+ "	AND `read` = 0")
	public int findAlarmCnt(Long uid);
	
	@Delete("DELETE FROM alarm WHERE id = #{id}")
	public void deleteAlarm(Long id);
}
