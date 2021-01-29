package com.shiv.service;

import java.util.List;

import com.shiv.domain.DayPassDetails;
import com.shiv.domain.MonthPassDetails;
import com.shiv.domain.YearPassDetails;
import com.shiv.model.User;

public interface UserService {

	public Object saveUser(User user);
	public Object getDayPass(int id, double dayPassFees);
	public User getMonthPass(int id, double monthPassFees);
	public User getYearPass(int id, double yearPassFees);
	public Object forgotPassword(User user);
	public List<DayPassDetails> findDayPassHistoryByAdharNumber(String adharNumber);
	public List<MonthPassDetails> findMonthPassHistoryByAdharNumber(String adharNumber);
	public List<YearPassDetails> findYearPassHistoryByAdharNumber(String adharNumber);
	public void clearDayPassHistory();
	public void clearMonthPassHistory();
	public void clearYearPassHistory();
}
