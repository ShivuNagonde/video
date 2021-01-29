package com.shiv.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiv.domain.DayPassDetails;
import com.shiv.domain.MonthPassDetails;
import com.shiv.domain.YearPassDetails;
import com.shiv.model.DayPassHistory;
import com.shiv.model.MonthPassHistory;
import com.shiv.model.User;
import com.shiv.model.YearPassHistory;
import com.shiv.repository.DayPassHistoryRepository;
import com.shiv.repository.MonthPassHistoryRepository;
import com.shiv.repository.UserRepository;
import com.shiv.repository.YearPassHistoryRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	Calendar cal1 = Calendar.getInstance();
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserHelper userHelper;
	@Autowired
	private DayPassHistoryRepository dayPassHistoryRepository;
	@Autowired
	private MonthPassHistoryRepository monthPassHistoryRepository;
	@Autowired
	private YearPassHistoryRepository yearPassHistoryRepository;
	@Override
	public Object saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public Object getDayPass(int id, double dayPassFees) {
		User user = userRepo.findById(id).get();
		DayPassHistory day = new DayPassHistory();
        cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMaximum(Calendar.HOUR_OF_DAY));
		if(user != null) {
		user.setDayPassFees("Rs."+dayPassFees);
		user.setCreatedDayPass(new Date());
		user.setExpiredDayPass(cal1.getTime());
		userRepo.save(user);
		day.setId(user.getId());
		day.setName(user.getName());
		day.setAdharNumber(user.getAdharNumber());
		day.setCreatedDayPass(user.getCreatedDayPass());
		day.setExpiredDayPass(user.getExpiredDayPass());
	    dayPassHistoryRepository.save(day);

		}
		return userRepo.getOne(id);
	}

	@Override
	public User getMonthPass(int id, double monthPassFees) {
		User user = userRepo.findById(id).get();
		MonthPassHistory month = new MonthPassHistory();
	    cal1.set(Calendar.DAY_OF_MONTH, cal1.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMaximum(Calendar.HOUR_OF_DAY));
	    //System.out.println(cal1.getTime());
		if(user != null) {
		user.setMonthPassFees("Rs."+monthPassFees);
	    user.setCreatedMonthPass(new Date());
	    user.setExpiredMonthPass(cal1.getTime());
	    userRepo.save(user);
	    month.setName(user.getName());
	    month.setAdharNumber(user.getAdharNumber());
	    month.setCreatedMonthPass(user.getCreatedMonthPass());
	    month.setExpiredMonthPass(user.getExpiredMonthPass());
	    monthPassHistoryRepository.save(month);
		}
		return userRepo.getOne(id);
	}

	@Override
	public User getYearPass(int id, double yearPassFees) {
		//User u = userRepo.findById(id).get();
		Optional<User> u1 = userRepo.findById(id);
        if(u1.isPresent()) {
        	User u = u1.get();
        
		
		YearPassHistory year = new YearPassHistory();
		//Year y = Year.of(2022); 
		if(u != null) {
		u.setYearPassFees("Rs."+yearPassFees);
	    u.setCreatedYearPass(new Date());
	    u.setExpiredYearPass("2022-03-31");
	    userRepo.save(u);
	    year.setStudentName(u.getName());
	    year.setAdharNumber(u.getAdharNumber());
	    year.setCreatedYearPass(u.getCreatedYearPass());
	    year.setExpiredYearPass(u.getExpiredYearPass());
	    yearPassHistoryRepository.save(year);
		}}
		return userRepo.getOne(id);
	}

	
	  @Override 
	  public List<DayPassDetails> findDayPassHistoryByAdharNumber(String adharNumber) { 
	  List<DayPassDetails> dayPassDetails = new ArrayList<>();
	  Optional<User> u = userRepo.findByAdharNumber(adharNumber); 
	  if(u.isPresent()) {
		  Optional<List<DayPassHistory>> d = dayPassHistoryRepository.findByAdharNumber(adharNumber); 
		  if(d.isPresent()) {
	            d.get().forEach(dayPassHistory -> {
	               dayPassDetails.add(userHelper.convertedToDayPassDomain(dayPassHistory)); 
	          });
	      } 
	  } 
	  return dayPassDetails;
	  }
	 

	@Override
	public List<MonthPassDetails> findMonthPassHistoryByAdharNumber(String adharNumber) {
		List<MonthPassDetails> monthPassDetails = new ArrayList<>();
		Optional<User> u = userRepo.findByAdharNumber(adharNumber);
		if(u.isPresent()) {
			Optional<List<MonthPassHistory>> m = monthPassHistoryRepository.findByAdharNumber(adharNumber);
		    if(m.isPresent()) {
		    	m.get().forEach(monthPassHistory -> {
		    		monthPassDetails.add(userHelper.convertedToMonthPassDomain(monthPassHistory));	
		    	});
		    }
		}
		return monthPassDetails;
	}

	@Override
	public List<YearPassDetails> findYearPassHistoryByAdharNumber(String adharNumber) {
		List<YearPassDetails> yearPassDetails = new ArrayList<>();
		Optional<User> u = userRepo.findByAdharNumber(adharNumber);
		if(u.isPresent()) {
			Optional<List<YearPassHistory>> y = yearPassHistoryRepository.findByAdharNumber(adharNumber);
		    if(y.isPresent()) {
		    	y.get().forEach(yearPassHistory -> {
		    	yearPassDetails.add(userHelper.convertedToYearPassDomain(yearPassHistory));
		    	});
		    }
		}
		return yearPassDetails;
	}

	@Override
	public Object forgotPassword(User user) {
		User u = userRepo.findByEmail(user.getEmail());
		if(u!=null) {
			if(u.getEmail().equals(user.getEmail())) {
				u.setPassword(user.getPassword());
			}else {
				return "Invalide Email. "+user.getEmail();
			}
		}
		return userRepo.save(u);
	}

	@Override
	public void clearDayPassHistory() {
		dayPassHistoryRepository.deleteAll();
	}

	@Override
	public void clearMonthPassHistory() {
		monthPassHistoryRepository.deleteAll();
		
	}

	@Override
	public void clearYearPassHistory() {
		yearPassHistoryRepository.deleteAll();
		
	}
	
}
