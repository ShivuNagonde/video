package com.shiv.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class YearPassHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String studentName;	
	private String adharNumber;	
	private Date createdYearPass;	
	private String expiredYearPass;
}
