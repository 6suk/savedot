package com.mulcam.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.finalproject.dao.CashDao;
import com.mulcam.finalproject.entity.Cash;

@Service
public class CashListServiceImpl implements CashListService {

	@Autowired
	private CashDao cashDao;

	@Override
	public List<Cash> getList(String uid, String startDate, String endDate) {
		List<Cash> list = cashDao.getList(uid, startDate, endDate);
		return list;
	}

	@Override
	public int sumNowExpense(String uid) { 
		int getTodayExpense = cashDao.sumNowExpense(uid);
		if(getTodayExpense==0) {
			return 0; // 오늘 등록한게 없을 때 어떻게 해야하지,,,?
		}
		return getTodayExpense;
	}

	@Override
	public int sumNowIncome(String uid) {
		int getTodayIncome = cashDao.sumNowIncome(uid);
		if(getTodayIncome == 0 ) {
			return 0; // 오늘 등록한게 없을 때 어떻게 해야하지,,,?
		}
		return getTodayIncome;
	}

	@Override
	public List<Cash> getAllCashList(String uid) {
		List<Cash> allCashList = cashDao.getAllCashList(uid);
		return allCashList;
	}


	}

