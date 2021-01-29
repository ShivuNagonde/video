package com.shiv.service;

import com.shiv.model.Admission;

public interface AdmissionService {

	Object studentAdmission(Admission admission);

	Object getAllAdmissions();

	Object findByCollegeId(String collegeId);

	Object deleteByCollegeId(String collegeId);

	void deleteAll();

}
