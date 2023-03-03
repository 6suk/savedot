package com.mulcam.finalproject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mulcam.finalproject.dto.AlarmDTO;
import com.mulcam.finalproject.dto.UserDTO;

public interface AlarmService {
	
	/** 알림 저장 */
	public void save(AlarmDTO alarmDTO);
	
	/** 대댓글 작성 시 알림 저장 */
	public void ReplyGrpSave(AlarmDTO alarmDTO);

	/** 알림 리스트 불러오기 */
	public List<AlarmDTO> findAlarmsByUid(Long uid);
	
	/** 알림 리스트 불러오기 */
	public List<AlarmDTO> findAlarmsByUid(UserDTO user);
	
	/** 알림 확인 시 상태 변경 */
	public void editType(List<AlarmDTO> list);
	
	/** 알림 Count 가져오기 */
	public int findAlarmCnt(UserDTO user);
	
	/** 지난 알림 삭제 */
	public void deleteAlarm(List<Long> id);
	
	/** 알림여부 */
	public void listCountBoolean(List<AlarmDTO> list, HttpSession session);
}
