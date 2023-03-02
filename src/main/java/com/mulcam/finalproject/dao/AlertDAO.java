package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.finalproject.entity.Alert;
import com.mulcam.finalproject.entity.MateReply;

@Mapper
public interface AlertDAO {
	
	@Insert("INSERT INTO alert"
			+ "	(id, `type`, toUid, fromUid, rid, `mid`, aid, alertDate, `read`)"
			+ "	VALUES (DEFAULT, #{type}, #{toUid}, #{fromUid}, #{rid}, #{mid}, #{aid}, DEFAULT, DEFAULT)")
	public void save(Alert alert);
	
	/** 그룹별 작성자 uid 가져오기 (알림용)*/
	@Select("SELECT DISTINCT(uid) FROM mate_reply"
			+ " WHERE `mid` = #{mid}"
			+ " AND grp = #{grp}"
			+ " AND uid != #{uid}")
	public List<Long> findGrpUidByMid(MateReply mateReply);
	
	/** apply 신청 취소 시 알림 삭제 */
	@Delete("DELETE FROM alert WHERE aid = #{aid};")
	public void delete(Long aid);
}
