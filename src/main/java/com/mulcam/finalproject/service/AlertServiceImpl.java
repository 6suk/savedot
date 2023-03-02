package com.mulcam.finalproject.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.AlertDAO;
import com.mulcam.finalproject.dto.AlertDTO;
import com.mulcam.finalproject.entity.Alert;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	AlertDAO alertDAO;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public void save(AlertDTO alertDTO) {
		Alert alert = modelMapper.map(alertDTO, Alert.class);
		alertDAO.save(alert);
	}
	
	@Override
	public void ReplyGrpSave(AlertDTO alertDTO) {
		List<Long> uidGroup = alertDAO.findGrpUidByMid(alertDTO.getReply());
		uidGroup.forEach(uid -> {
			alertDTO.setToUid(uid);
			save(alertDTO);
		});
	}

}
