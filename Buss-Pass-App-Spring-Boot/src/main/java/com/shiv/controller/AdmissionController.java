package com.shiv.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shiv.model.Admission;
import com.shiv.repository.AdmissionRepository;
import com.shiv.service.AdmissionService;

@RestController
@RequestMapping("/student")
public class AdmissionController {

	@Autowired
	private AdmissionService admissionService;
	@Autowired
	private AdmissionRepository admissionRepository;
	
	@PostMapping(value = "/admission")
	public Object admissions(@RequestParam("Image") MultipartFile file,@RequestParam("Signature") MultipartFile file1,
			@RequestParam("AdharCard") MultipartFile file3, Admission admission) throws IOException {
		if(admissionRepository.existsByEmail(admission.getEmail())) {
			return "this emailId already existed.";
		}
		if(admissionRepository.existsByAdharNumber(admission.getAdharNumber())) {
			return "this adharNumber already existed.";
		}
		String encodedeString = encodeFileToBase64Binary(file.getBytes());
		String encodedeString1 = encodeFileToBase64Binary(file1.getBytes());
		String encodedString2 = encodeFileToBase64Binary(file3.getBytes());

		admission.setAdmissionDate(new Date());
		admission.setStudentImage(encodedeString);
		admission.setStudentSignature(encodedeString1);
		admission.setStudentAdharCard(encodedString2);
		return admissionService.studentAdmission(admission);}
	
	@GetMapping(value = "/getAdmissions")
	public ResponseEntity<?> getAllAdmissions(){
		return ResponseEntity.status(HttpStatus.OK).body(admissionService.getAllAdmissions());
	}
	
	@GetMapping(value = "/getStudent/{collegeId}")
	public Object getAdmissions(@PathVariable String collegeId){
		Admission ad = admissionRepository.findByCollegeId(collegeId);
		Map<String,Object> map = new HashMap<>();
		if(ad!=null) {
			map.put("Success..", admissionService.findByCollegeId(collegeId));
		}else {
			map.put("Sorry!,", "invalid collegeId: "+collegeId);
		}
		return map;
	}
	@PutMapping(value = "/update")
	public Object updateAdmission(@RequestParam("adharCard") MultipartFile file1,
			@RequestParam("signature") MultipartFile file2,@RequestParam("image") MultipartFile file3,
			Admission admission) throws IOException {
		Admission ad = admissionRepository.findByCollegeId(admission.getCollegeId());
		if(ad!=null) {
			ad.setUpdatedDate(new Date());
			ad.setPhoneNumber(admission.getPhoneNumber());
			ad.setStudentAdharCard(encodeFileToBase64Binary(file1.getBytes()));
			ad.setStudentSignature(encodeFileToBase64Binary(file2.getBytes()));
			ad.setStudentImage(encodeFileToBase64Binary(file3.getBytes()));
			admissionRepository.save(ad);
		}
		return admission;
    }
	@DeleteMapping(value = "/deleteAdmission/{collegeId}")
	public Object deleteAdmissionByCollegeId(@PathVariable("collegeId") String collegeId) {
		Admission ad = admissionRepository.findByCollegeId(collegeId);
		Map<String,Object> map=new HashMap<>();
		if(ad!=null) {
			map.put("data deleted.", admissionService.deleteByCollegeId(collegeId));
		}
		else {
			map.put("Sorry!,", "collegeId "+collegeId+" is not found.");
		}
		return map;
	}
	@DeleteMapping(value = "/deleteAllAdmissions")
	public Object deleteAllAdmissions() {
		admissionService.deleteAll();
		return "all admissions are deleted.";
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
}
/*
 * if(admissionRepository.existsByEmail(admission.getEmail())) { return
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Existed.");
 * } if(admissionRepository.existsByAdharNumber(admission.getAdharNumber())) {
 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).
 * body("AdharNumber Already Existed."); }
 */