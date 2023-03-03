package com.mulcam.finalproject.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.AlarmDAO;
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
	AlarmDAO alertDAO;

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
		alertDAO.delete(aid);
	}

	@Override
	public List<MateApplyDTO> findBySendUid(Long uid) {
		List<MateApply> list = applyDAO.findBySendUid(uid);
		return mapperDTO(list);
	}

	@Override
	public List<MateApplyDTO> findBySendUid(User user) {
		return findBySendUid(user.getUid());
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
		return findByGetUid(user.getUid());
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
	public LocalDateTime editIsApply(MateApplyDTO mateApplyDTO) {
		applyDAO.editIsApply(mateApplyDTO.getAid(), mateApplyDTO.getIsApply());

		// 대기중, 진행중 -> 거래 완료로 변경 시 Mate 모집인원 +1
		if (mateApplyDTO.getIsApply() == 2) {
			mateService.updateAddApply(mateApplyDTO.getMid());
		}

		// 거래 완료 -> 대기중, 진행중 변경 시 Mate 모집인원 -1
		if (mateApplyDTO.getBeforeIsApply() == 2 && mateApplyDTO.getIsApply() != 2) {
			mateService.updateCancelApply(mateApplyDTO.getMid());
		}

		return applyDAO.findEditTime(mateApplyDTO.getAid());
	}
	
	public MateApplyDTO findOneByAid(Long aid) {
		List<MateApply> list = new ArrayList<>();
		list.add(applyDAO.findOneByAid(aid));
		return mapperDTO(list).get(0);
	}

	public List<MateApplyDTO> mapperDTO(List<MateApply> list) {
		List<MateApplyDTO> dtoList = new ArrayList<>();

		// mapper
		ModelMapper modelMapper = new ModelMapper();
		list.forEach(entity -> {
			MateApplyDTO applyDTO = modelMapper.map(entity, MateApplyDTO.class);
			Long mid = entity.getMid();
			Long uid = entity.getUid();
			MateDTO mateDTO = mateService.findOneByMid(mid);
			UserDTO userDTO = userService.findByUid(uid);
			userDTO.setPwd("");
			applyDTO.setUser(userDTO);
			applyDTO.setMate(mateDTO);
			dtoList.add(applyDTO);
		});

		return dtoList;
	}
	

}
