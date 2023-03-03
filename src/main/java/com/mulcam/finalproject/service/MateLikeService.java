package com.mulcam.finalproject.service;

import java.util.List;

import com.mulcam.finalproject.entity.MateLike;

public interface MateLikeService {
	
	List<MateLike> GetLikeList(long uid);
	
	void insertLike(MateLike mateLike);
	
	void cancelLike(long mid);
	
	void plusLike(long mid);
	
	void deleteLike(long mid,long uid);


}
