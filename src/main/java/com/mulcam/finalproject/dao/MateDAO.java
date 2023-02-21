package com.mulcam.finalproject.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.finalproject.dto.MateSearchDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateImg;

@Mapper
public interface MateDAO {
	
	/** SAVE */
	@Insert("INSERT INTO mate"
			+ "	(`mid`, uid, category, state, title, content, positionNum, positonApplyNum, accountNumber, bank, isDel, likeCnt, modDate, price1, price2, tradeType, placeAddr, placeCoords, placeName, placeCode, parcelType, parcelPrice, telType, telUrl, replyCnt, viewCnt)"
			+ "	VALUES (DEFAULT, #{uid}, #{category}, DEFAULT, #{title}, #{content}, #{positionNum}, DEFAULT, #{accountNumber}, #{bank}, DEFAULT, DEFAULT, DEFAULT, #{price1}, #{price2}, #{tradeType}, #{placeAddr}, #{placeCoords}, #{placeName}, #{placeCode}, #{parcelType}, #{parcelPrice}, #{telType}, #{telUrl}, DEFAULT, DEFAULT)")
	public void save(Mate mate);
	
	@Select("SELECT LAST_INSERT_ID();")
	public Long findSaveId();
	
	@Insert("INSERT INTO mate_img"
			+ "	(iid, `mid`, ext, filePath, origFileName, saveDate)"
			+ "	VALUES (UUID_TO_BIN(#{id}), #{mid}, #{ext}, #{filePath}, #{origFileName}, #{saveDate})")
	public void ImgSave(MateImg mateImg);
	
	
	/** FINDONE */
	@Select("SELECT * FROM mate"
			+ "	WHERE isDel = 0"
			+ "	AND mid = #{mid};")
	public Mate findOneByMid(Long mid);
	
	@Select("SELECT (BIN_TO_UUID(iid)) AS id, `mid`, ext, filePath, origFileName, saveDate FROM mate_img"
			+ "	WHERE MID = #{mid};")
	public List<MateImg> findImgsByMid(Long mid);
	
	/** UPDATE : APPLY COUNT */
	@Update("UPDATE mate"
			+ "	SET positonApplyNum = positonApplyNum + 1,"
			+ "	state = if(positionNum = positonApplyNum,1,0)"
			+ "	WHERE `mid` = #{mid};")
	public void updateAddApply(Long mid);
	
	@Update("UPDATE mate"
			+ "	SET positonApplyNum = positonApplyNum - 1,"
			+ "	state = if(positionNum = positonApplyNum,1,0)"
			+ "	WHERE `mid` = #{mid};")
	public void updateCancelApply(Long mid);
	
	/** UPDATE : APPLY COUNT */
	@Select("SELECT * FROM mate"
			+ "	 WHERE isDel = 0"
			+ "	AND category IN(${categorySQL})"
			+ "	AND state IN(${stateSQL})"
			+ "	AND placeCode IN(${areaSQL})"
			+ "	AND title LIKE '%${querySQL}%'"
			+ "	ORDER BY modDate DESC;")
	public List<Mate> findAllBySearch(MateSearchDTO mateSearchDTO);


}
