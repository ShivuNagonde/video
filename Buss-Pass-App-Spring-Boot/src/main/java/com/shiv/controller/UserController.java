package com.shiv.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shiv.domain.DayPassDetails;
import com.shiv.domain.MonthPassDetails;
import com.shiv.domain.YearPassDetails;
import com.shiv.model.User;
import com.shiv.repository.UserRepository;
import com.shiv.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json")
	public Object loginUser(@RequestBody User user) {
		User email = userRepo.findByEmail(user.getEmail());
		Map<String, Object> map = new HashMap<>();
		if(email != null) {
			if(email.getPassword().equals(user.getPassword())) {
				map.put("Well Come", "Login Successful.");
			}else{
				map.put("try again!", "Invalide Password.");
			}}else {
				map.put("try again!", "Invalide Email.");
			}
		return map;
    }
	
	@RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json")
	public Object saveUsers(@RequestParam("file") MultipartFile file, User user) throws IOException {
		if(userRepo.existsByEmail(user.getEmail())) {
			return "emailId already existed.";}
		if(userRepo.existsByPassword(user.getPassword())) {
			return "password already existed.";}
		
		String encodeString = encodeFileToBase64Binary(file.getBytes());
		user.setImage(encodeString);
		return userService.saveUser(user);
	}
	
	@RequestMapping(value = "/forgotPassword",method = RequestMethod.POST,produces = "application/json")
	public Object forgotPassword(@RequestBody User user) {
	    return userService.forgotPassword(user);
	}
	@RequestMapping(value = "/dayPass",method = RequestMethod.GET,produces = "application/json")
	public Object getDayPass(int id, double dayPassFees) {
		return userService.getDayPass(id, dayPassFees);
	}
	
	@RequestMapping(value = "/monthPass",method = RequestMethod.GET,produces = "application/json")
	public User getMonthPass(int id, double monthPassFees) {
		return userService.getMonthPass(id, monthPassFees);
	}
	
	@RequestMapping(value = "/yearPass",method = RequestMethod.POST,produces = "application/json")
	public Object getYearPass(int id, double yearPassFees,String startFrom,String endTo,String collegeName,
            @RequestParam("feesReciept") MultipartFile multipartFile,
            @RequestParam("signature") MultipartFile multipartFile1,
            @RequestParam("adharCard") MultipartFile multipartFile2
            ) throws IOException {
		User user = userRepo.findById(id).get();
	
		String encodeString1 = encodeFileToBase64Binary(multipartFile.getBytes());
		String encodeString2 = encodeFileToBase64Binary(multipartFile1.getBytes());
		String encodeString3 = encodeFileToBase64Binary(multipartFile2.getBytes());

		if(user.getFeesReciept().equals(encodeString1)) {
			return "FeesReciept Already existed.";
		}else {
			user.setCollegeName(collegeName);
			user.setFeesReciept(encodeString1);
			user.setSignature(encodeString2);
			user.setAdharCard(encodeString3);
			user.setStartFrom(startFrom);
			user.setEndTo(endTo);
			userService.saveUser(user);
		}
		return userService.getYearPass(id, yearPassFees);
	}
	
	@GetMapping(value = "/dayPassHistory/{adharNumber}")
	public List<DayPassDetails> getDayPassHistoryById(@PathVariable String adharNumber){
		return userService.findDayPassHistoryByAdharNumber(adharNumber);
	}
	
	@GetMapping(value = "/monthPassHistory/{adharNumber}")
	public List<MonthPassDetails> getMonthPassHistoryById(@PathVariable String adharNumber){
		return userService.findMonthPassHistoryByAdharNumber(adharNumber);
	}
	
	@GetMapping(value = "/yearPassHistory/{adharNumber}")
	public List<YearPassDetails> getYearPassHistoryById(@PathVariable String adharNumber){
		return userService.findYearPassHistoryByAdharNumber(adharNumber);
	}
	private String encodeFileToBase64Binary(byte[] bytes) {
		String encodedFile = "";
		try {
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        }catch (Exception e) {
            e.printStackTrace();
        }
		return encodedFile;
	}
	@DeleteMapping(value = "/clearDayPassHistory")
	public void clearDayPassHistory() {
		userService.clearDayPassHistory();
	}
	@DeleteMapping(value = "/clearMonthPassHistory")
	public void clearMonthPassHistory() {
		userService.clearMonthPassHistory();
	}
	@DeleteMapping(value = "/clearYearPassHistory")
	public void clearYearPassHistory() {
		userService.clearYearPassHistory();
	}
}
