package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.entity.MateApply;

@Mapper
public interface MateApplyDAO {

	@Insert("INSERT INTO mate_apply VALUES(DEFAULT, #{content}, DEFAULT, DEFAULT, DEFAULT, #{mid}, #{uid}, #{applyTradelType}, DEFAULT)")
	public void save(MateApply mateApply);

	@Select("SELECT LAST_INSERT_ID();")
	public Long findSaveId();

	@Update("UPDATE mate_apply"
			+ "	SET isDel = 1"
			+ "	WHERE aid = #{aid};")
	public void delete(Long aid);
	
	@Update("UPDATE mate_apply"
			+ "	SET isApply = #{isApply}"
			+ "	WHERE aid = #{aid};")
	public void updateIsApply(int isApply, Long aid);
	
	@Select("SELECT * FROM mate_apply WHERE isDel = 0 ORDER BY modDate DESC, regdate DESC;")
	public List<MateApply> findAll();
	
	/** SEND APPLY : 내가 신청한 리스트 조회 */
	@Select("SELECT * FROM mate_apply WHERE uid = #{uid} AND isDel = 0 ORDER BY modDate DESC;")
	public List<MateApply> findBySendUid(Long uid);
	
	/** GET APPLY : 내가 작성한 글의 신청 리스트 조회 */
	@Select("SELECT a.* FROM mate_apply AS a"
			+ "	JOIN mate AS m"
			+ "	ON a.`mid` = m.`mid`"
			+ "	WHERE m.uid = #{uid};")
	public List<MateApply> findByGetUid(Long uid);
	
	@Select("SELECT * FROM mate_apply WHERE mid = #{mid} AND isDel = 0 ORDER BY modDate DESC;")
	public List<MateApply> findByMid(Long mid);
	

}
