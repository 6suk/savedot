package com.mulcam.finalproject.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.dto.MateSearchDTO;
import com.mulcam.finalproject.entity.MateApply;

@Mapper
public interface MateApplyDAO {
	
	/** Apply Save */
	@Insert("INSERT INTO mate_apply"
			+ "	(aid, `mid`, uid, content, isApply, isDel, modDate, applyTradelType, regDate)"
			+ "	VALUES (DEFAULT, #{mid}, #{uid}, #{content}, DEFAULT, DEFAULT, DEFAULT, #{applyTradelType}, DEFAULT);")
	public void save(MateApply mateApply);

	@Select("SELECT LAST_INSERT_ID();")
	public Long findSaveId();
	
	/** Apply delete */
	@Update("UPDATE mate_apply"
			+ "	SET isDel = 1"
			+ "	WHERE aid = #{aid};")
	public void delete(Long aid);

	
	/** Apply 상태변경 */
	@Update("UPDATE mate_apply"
			+ "	SET isApply = #{isApply},"
			+ "	modDate = DEFAULT"
			+ "	WHERE aid = #{aid};")
	public void editIsApply(Long aid, int isApply);

	@Select("SELECT modDate FROM mate_apply"
			+ "	WHERE aid = #{aid};")
	public LocalDateTime findEditTime(Long aid);

	
	/** Apply 전체리스트 조회 (사용안함) */
	@Select("SELECT * FROM mate_apply WHERE isDel = 0 ORDER BY modDate DESC, regdate DESC;")
	public List<MateApply> findAll();

	/** SEND APPLY : 내가 신청한 리스트 조회 */
	@Select("SELECT * FROM mate_apply WHERE uid = #{uid} AND isDel = 0 ORDER BY modDate DESC, regDate DESC;")
	public List<MateApply> findBySendUid(Long uid);

	/** SEND APPLY New Notify : 오늘 상태 변경된 리스트 카운트 */
	@Select("SELECT COUNT(*) FROM mate_apply"
			+ "	WHERE CAST(modDate AS DATE) = CURDATE()"
			+ "	AND isDel = 0"
			+ "	AND uid = #{uid};")
	public int findNewBySendUid(Long uid);

	/** GET APPLY : 내가 작성한 글의 신청 리스트 조회 */
	@Select("SELECT a.* FROM mate_apply AS a"
			+ " JOIN mate AS m"
			+ " ON a.`mid` = m.`mid`"
			+ " WHERE a.isDel = 0 AND m.isDel = 0 AND m.uid = #{uid}"
			+ " ORDER BY a.modDate DESC, a.regDate DESC;")
	public List<MateApply> findByGetUid(Long uid);

	/** GET APPLY New Notify : 오늘 상태 변경된 리스트 카운트 */
	@Select("SELECT COUNT(*) FROM mate_apply AS a"
			+ " JOIN mate AS m"
			+ " ON a.`mid` = m.`mid`"
			+ " WHERE CAST(a.modDate AS DATE) = CURDATE()"
			+ "	AND a.isDel = 0"
			+ " AND m.uid = #{uid};")
	public int findNewByGetUid(Long uid);
	
	@Select("SELECT * FROM mate_apply"
			+ " WHERE aid = #{aid};")
	public MateApply findOneByAid(Long aid);
	
	/** APPLY : 받은, 보낸 조각메이트 신청 모두 받기 */
	@Select("SELECT a.*, m.uid AS getUid FROM mate_apply AS a"
			+ " JOIN mate AS m"
			+ " ON a.`mid` = m.`mid`"
			+ " WHERE a.isDel = 0"
			+ " AND m.isDel = 0"
			+ " AND a.isApply IN(${isApplySQL})"
			+ " AND (${sendBySQL})"
			+ " ORDER BY a.modDate DESC, a.regDate DESC;")
	public List<MateApply> findAllByUid(MateSearchDTO mateSearchDTO);


}
