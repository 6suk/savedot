package com.mulcam.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.entity.MateReply;

@Mapper
public interface MateReplyDAO {
	
	/* 댓글리스트 가져오기 */
	@Select("SELECT * FROM mate_reply WHERE isDeleted = 0 ORDER BY grp,grps")
	public List<MateReply> getReplies(); 
	
	/* 첫댓글 입력  */
	@Insert("INSERT INTO mate_reply VALUES (DEFAULT, #{uid}, #{mid}, #{nickname}, #{content}, DEFAULT, #{isMine}, #{grp}, DEFAULT, DEFAULT,DEFAULT)")
	void insertReply(MateReply reply);
	@Select("SELECT MAX(grp) FROM mate_reply")
	MateReply getMaxGrp(MateReply reply);
	
	
	/* 댓글-댓글 입력 */
	@Insert("INSERT INTO mate_reply Values (default , #{uid}, #{mid}, #{nickname}, #{content}, DEFAULT, #{isMine}, #{grp}, #{grps}, #{grpl},DEFAULT)")
	void insertReReply(MateReply reply);
	@Select("SELECT * FROM mate_reply where grp=#{grp}")
	MateReply getReply(int grp); 
	

	/* 댓글 삭제 */
	@Delete("DELETE FROM mate_reply WHERE rid=#{rid}")
	void deleteReply(Long rid);
	
	
	/* 댓글 수정 */ 
	@Update("UPDATE mate_reply SET content=#{content},regdate=#{regDate}"
			+ " WHERE uid=#{uid} AND rid=#{rid}")
	void updateReply(MateReply mateReply);
}
