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
	@Select("SELECT * FROM mate_reply WHERE mid=#{mid} ORDER BY grp, grps, regDate")
	public List<MateReply> getReplies(long mid); 
	
	/* 첫댓글 입력  */
	@Insert("INSERT INTO mate_reply VALUES (DEFAULT, #{uid}, #{mid}, #{nickname}, #{content}, DEFAULT, #{isMine}, #{grp}, DEFAULT)")
	void insertReply(MateReply reply);
	@Select("SELECT MAX(grp) FROM mate_reply where mid=#{mid}")
	int getMaxGrp(long mid);
	
	
	/* 댓글-댓글 입력 */
	@Insert("INSERT INTO mate_reply Values (default , #{uid}, #{mid}, #{nickname}, #{content}, DEFAULT, #{isMine}, #{grp}, #{grps})")
	void insertReReply(MateReply reply);
	@Select("SELECT * FROM mate_reply where grp=#{grp} AND mid=#{mid}")
	MateReply getReply(int grp,long mid); 
	@Select("SELECT * FROM mate_reply WHERE rid=#{rid} AND grp=#{grp}")
	MateReply getGrp(long rid, int grp);
	@Select("SELECT MAX(grps) FROM mate_reply where mid=#{mid} and grp=#{grp}")
	int getMaxGrps(long mid,int grp);

	/* 댓글 삭제 */
	@Delete("DELETE FROM mate_reply WHERE rid=#{rid}")
	void deleteReply(long rid);
	
	
	/* 댓글 수정 */ 
	@Update("UPDATE mate_reply SET content=#{content},regdate=CURRENT_TIMESTAMP"
			+ " WHERE rid=#{rid}")
	void updateReply(MateReply mateReply);	
	@Select("Select * from mate_reply where rid=#{rid}")
	MateReply getMateReply(long rid);
}
