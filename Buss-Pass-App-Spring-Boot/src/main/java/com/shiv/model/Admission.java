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
public class Admission {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
	private int id; 
	private String firstName; 
	private String middleName;
	private String lastName; 
	private String email;
	private int age;
	private String gender;
	private String collegeId;
	private String adharNumber; 
	private String phoneNumber;  
    private Date admissionDate;
    private Date updatedDate;
    @Lob 
    private String studentAdharCard; 
    @Lob 
    private String studentSignature; 
    @Lob 
    private String studentImage;
  }
 