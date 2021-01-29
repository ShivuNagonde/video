package com.shiv.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DayPassDetails {
	    
	    private String name;	
		private String adharNumber;	
		private Date createdDayPass;	
		private Date expiredDayPass;
}
