package com.mulcam.finalproject.service;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public void save(AlarmDTO alarmDTO) {
		Alarm alarm = modelMapper.map(alarmDTO, Alarm.class);
		alarmDAO.save(alarm);
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
			result.add(alarm);
		});

		return result;
	}

}
