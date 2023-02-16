package com.mulcam.finalproject.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.MateApplyDAO;
import com.mulcam.finalproject.dto.MateApplyDTO;
import com.mulcam.finalproject.dto.MateDTO;
import com.mulcam.finalproject.dto.UserDTO;
import com.mulcam.finalproject.entity.Mate;
import com.mulcam.finalproject.entity.MateApply;
import com.mulcam.finalproject.entity.User;

@Service
public class MateApplyServiceImpl implements MateApplyService {

	@Autowired
	MateApplyDAO applyDAO;

	@Autowired
	MateService mateService;

	@Autowired
	UserService userService;

	@Override
	public Long save(MateApply mateApply) {
		applyDAO.save(mateApply);
		return applyDAO.findSaveId();
	}

	@Override
	public void delete(Long aid) {
		applyDAO.delete(aid);
	}

	@Override
	public List<MateApplyDTO> findBySendUid(Long uid) {
		List<MateApply> list = applyDAO.findBySendUid(uid);
		return mapperDTO(list);
	}

	@Override
	public List<MateApplyDTO> findBySendUid(User user) {
		return findBySendUid(user.getIdAuto());
	}
	
	@Override
	public boolean findNewBySendUid(Long uid) {
		int count = applyDAO.findNewBySendUid(uid);
		return count > 0 ? true : false;
	}

	@Override
	public List<MateApplyDTO> findByGetUid(Long uid) {
		List<MateApply> list = applyDAO.findByGetUid(uid);
		return mapperDTO(list);
	}

	@Override
	public List<MateApplyDTO> findByGetUid(User user) {
		return findByGetUid(user.getIdAuto());
	}
	
	@Override
	public boolean findNewByGetUid(Long uid) {
		int count = applyDAO.findNewByGetUid(uid);
		return count > 0 ? true : false;
	}

	@Override
	public List<MateApplyDTO> findByMid(Mate mate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime editIsApply(Long aid, int isApply) {
		applyDAO.editIsApply(aid, isApply);
		return applyDAO.findEditTime(aid);
	}

	public List<MateApplyDTO> mapperDTO(List<MateApply> list) {
		List<MateApplyDTO> dtoList = new ArrayList<>();
		
		// mapper
		ModelMapper modelMapper = new ModelMapper();
		list.forEach(entity -> {
			MateApplyDTO applyDTO = modelMapper.map(entity, MateApplyDTO.class);
			Long mid = entity.getMid();
			Long uid = entity.getUid();
			MateDTO mateDTO = modelMapper.map(mateService.findById(mid).get(), MateDTO.class);
			UserDTO userDTO = modelMapper.map(userService.findById(uid).get(), UserDTO.class);
			applyDTO.setUser(userDTO);
			applyDTO.setMate(mateDTO);
			dtoList.add(applyDTO);
		});

		return dtoList;
	}





}
