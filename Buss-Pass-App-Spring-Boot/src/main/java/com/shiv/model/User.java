package com.shiv.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String password;
	private String adharNumber;
	private String phoneNumber;
	private String collegeName;
	private String startFrom;
	private String endTo;
	private String dayPassFees;
	private String monthPassFees;
	private String yearPassFees;
	@Lob
	private String image;
	@Lob
	private String adharCard;
	@Lob
	private String feesReciept;
	@Lob
	private String signature;
	private Date createdDayPass;
	private Date expiredDayPass;
	private Date createdMonthPass;
	private Date expiredMonthPass;
	private Date createdYearPass;
	private String expiredYearPass;
	
	//@OneToOne(targetEntity = DayPassHistory.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//@JoinColumn(name = "user_id",referencedColumnName = "id")
	//private DayPassHistory dayPassHistory;
}
