package com.mulcam.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.MateLikeDAO;
import com.mulcam.finalproject.entity.MateLike;

@Service
public class MateLikeServiceImpl implements MateLikeService {
	
	@Autowired
	MateLikeDAO likeDAO;

	@Override
	public List<MateLike> GetLikeList(long uid) {
		List<MateLike> list = likeDAO.GetLikeList(uid);
		return list;
	}

	@Override
	public void insertLike(MateLike mateLike) {
		likeDAO.insertLike(mateLike);
	}

	@Override
	public void deleteLike(long mid, long uid) {
		likeDAO.deleteLike(mid, uid);
	}

	@Override
	public void cancelLike(long mid) {
		likeDAO.cancelLike(mid);
	}

	@Override
	public void plusLike(long mid) {
		likeDAO.plusLike(mid);
	}



}
