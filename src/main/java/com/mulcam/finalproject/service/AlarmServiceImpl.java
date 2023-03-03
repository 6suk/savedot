package com.mulcam.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.AlarmDAO;
import com.mulcam.finalproject.dto.AlarmDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Alarm;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmDAO alarmDAO;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	MateService mateService;

	@Autowired
	UserService userService;

	@Autowired
	MateReplyService replyService;
	
	@Autowired
	MateApplyService applyService;

	@Override
	public void save(AlarmDTO alarmDTO) {
		if(alarmDTO.getToUid() != alarmDTO.getFromUid()) {
			Alarm alarm = modelMapper.map(alarmDTO, Alarm.class);
			alarmDAO.save(alarm);
		}
	}

	@Override
	public void ReplyGrpSave(AlarmDTO alarmDTO) {
		List<Long> uidGroup = alarmDAO.findGrpUidByMid(alarmDTO.getReply());
		uidGroup.forEach(uid -> {
			alarmDTO.setToUid(uid);
			save(alarmDTO);
		});
	}

	@Override
	public List<AlarmDTO> findAlarmsByUid(UserDTO user) {
		return findAlarmsByUid(user.getUid());
	}

	@Override
	public List<AlarmDTO> findAlarmsByUid(Long uid) {
		List<Alarm> list = alarmDAO.findAlarmsByUid(uid);
		List<AlarmDTO> result = new ArrayList<>();

		list.forEach(x -> {
			AlarmDTO alarm = modelMapper.map(x, AlarmDTO.class);
			alarm.setMate(mateService.findOneByMid(x.getMid()));
			alarm.setUser(userService.findByUid(x.getFromUid()));
			if (x.getRid() != null) {
				alarm.setReply(replyService.getMateReply(x.getRid()));
			}
			if(x.getAid() != null) {
				alarm.setApply(applyService.findOneByAid(x.getAid()));
			}
			result.add(alarm);
		});
		return result;
	}
	
	public void listCountBoolean(List<AlarmDTO> list, HttpSession session) {
		boolean read = false;
		boolean noneread = false;
		
		for(AlarmDTO x : list) {
			if(x.getRead() == 0 && !noneread) {
				noneread = true;
			}else if(x.getRead() == 1 && !read) {
				read = true;
			}
		}
		
		session.setAttribute("read", read);
		session.setAttribute("noneread", noneread);
		
	}
	
	@Override
	public void editType(List<AlarmDTO> list) {
		list.forEach(x -> {
			Alarm alarm = modelMapper.map(x, Alarm.class);
			alarmDAO.editType(alarm);
		});
	}
	
	@Override
	public int findAlarmCnt(UserDTO user) {
		return alarmDAO.findAlarmCnt(user.getUid());
	}
	
	public void deleteAlarm(List<Long> id) {
		id.forEach(x -> alarmDAO.deleteAlarm(x));
	}

}
