package com.mulcam.finalproject.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		int getTodayExpense = 0;
		try { 
			getTodayExpense = cashDao.sumNowExpense(uid);
		} catch (Exception e) {	}
		return getTodayExpense;
	}

	@Override
	public int sumNowIncome(String uid) {
		
		int getTodayIncome = 0;
		try { 
			getTodayIncome = cashDao.sumNowIncome(uid);
		} catch (Exception e) {	}
		
		return getTodayIncome;
	}

	@Override
	public Map<String, List<Cash>> getCashListByPeriod(String uid, String startDate, String endDate) {
		LocalDate startDay = LocalDate.parse(startDate);
		LocalDate endDay = LocalDate.parse(endDate);
		int year = startDay.getYear();
		int month = startDay.getMonthValue();
		
		Comparator<String> comparator = Comparator.reverseOrder();
		Map<String, List<Cash>> map =  new TreeMap<>(comparator);
		for(int i=startDay.getDayOfMonth();i<=endDay.getDayOfMonth();i++) {
			String date = String.format("%d-%02d-%02d", year, month, i);
			List<Cash> list = cashDao.getCashListByDate(uid, date);
			if(list.size()==0)
				continue;
			map.put(date, list);
		}
		
		return map;
	}


}

