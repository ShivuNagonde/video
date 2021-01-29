package com.shiv.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiv.model.Admission;
import com.shiv.repository.AdmissionRepository;

@Service
@Transactional
public class AdmissionServiceImpl implements AdmissionService {

	@Autowired
	private AdmissionRepository admissionRepository;
	
	@Override
	public Object studentAdmission(Admission admission) {
		return admissionRepository.save(admission);
	}

	@Override
	public Object getAllAdmissions() {
		return admissionRepository.findAll();
	}

	@Override
	public Object findByCollegeId(String collegeId) {
		return admissionRepository.findByCollegeId(collegeId);
	}

	@Override
	public Object deleteByCollegeId(String collegeId) {
		return admissionRepository.deleteByCollegeId(collegeId);
	}

	@Override
	public void deleteAll() {
		admissionRepository.deleteAll();
	}

}
