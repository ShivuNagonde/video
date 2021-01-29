package com.shiv.repository;
  
import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.model.Admission;
  
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {

	boolean existsByEmail(String email);

	boolean existsByAdharNumber(String adharNumber);

	//boolean existsByFirstName(String firstName);

	Admission findByCollegeId(String collegeId);

	Object deleteByCollegeId(String collegeId);
  
  }
