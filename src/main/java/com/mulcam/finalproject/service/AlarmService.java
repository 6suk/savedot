package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.dto.AlarmDTO;
import com.mulcam.finalproject.dto.UserDTO;

public interface AlarmService {
	public void save(AlarmDTO alarmDTO);

	public void ReplyGrpSave(AlarmDTO alarmDTO);

	public List<AlarmDTO> findAlarmsByUid(Long uid);
	
	public List<AlarmDTO> findAlarmsByUid(UserDTO user);
}
