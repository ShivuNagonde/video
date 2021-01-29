package com.shiv.service;

import org.springframework.stereotype.Component;

import com.shiv.domain.DayPassDetails;
import com.shiv.domain.MonthPassDetails;
import com.shiv.domain.YearPassDetails;
import com.shiv.model.DayPassHistory;
import com.shiv.model.MonthPassHistory;
import com.shiv.model.YearPassHistory;

@Component
public class UserHelper {

	public DayPassDetails convertedToDayPassDomain(DayPassHistory dayPassHistory ) {
		return DayPassDetails.builder()
				.name(dayPassHistory.getName())
				.adharNumber(dayPassHistory.getAdharNumber())
				.createdDayPass(dayPassHistory.getCreatedDayPass())
				.expiredDayPass(dayPassHistory.getExpiredDayPass())
				.build();
		}
	
	public MonthPassDetails convertedToMonthPassDomain(MonthPassHistory monthPassHistory ) {
		return MonthPassDetails.builder()
				.name(monthPassHistory.getName())
				.adharNumber(monthPassHistory.getAdharNumber())
				.createdMonthPass(monthPassHistory.getCreatedMonthPass())
				.expiredMonthPass(monthPassHistory.getExpiredMonthPass())
				.build();
		}
	
	public YearPassDetails convertedToYearPassDomain(YearPassHistory yearPassHistory ) {
		return YearPassDetails.builder()
				.studentName(yearPassHistory.getStudentName())
				.adharNumber(yearPassHistory.getAdharNumber())
				.createdYearPass(yearPassHistory.getCreatedYearPass())
				.expiredYearPass(yearPassHistory.getExpiredYearPass())
				.build();
		
	}
}
