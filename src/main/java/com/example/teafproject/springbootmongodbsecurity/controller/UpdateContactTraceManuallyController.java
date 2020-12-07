package com.example.teafproject.springbootmongodbsecurity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teafproject.springbootmongodbsecurity.domain.UpdateContactTraceManually;
import com.example.teafproject.springbootmongodbsecurity.repository.UpdateContactTraceManuallyDAO;
import com.example.teafproject.springbootmongodbsecurity.service.impl.UpdateContactTraceManuallyServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UpdateContactTraceManuallyController {

	@Autowired
	UpdateContactTraceManuallyDAO updateContactTraceManuallyDao;

	@Autowired
	UpdateContactTraceManuallyServiceImpl updateContactTraceManuallyService;

	@PostMapping(value = "/contactmanually")
	public ResponseEntity<?> saveContactTraceData(@RequestBody UpdateContactTraceManually updateContactTraceManually) {
		updateContactTraceManuallyDao.save(updateContactTraceManually);
		return new ResponseEntity("Update Contact Trace Manually data saved successfully", HttpStatus.OK);
	}

	@GetMapping(value = "/getcontacttraceall")
	public ResponseEntity<?> ContactTraceDataAll() {

		List<UpdateContactTraceManually> updateContactTraceList =  updateContactTraceManuallyDao.findAll();
		System.out.println("list size = "+ updateContactTraceList.size());
		if (updateContactTraceList !=null) {
			return new ResponseEntity(updateContactTraceList, HttpStatus.OK);
		} else {
			return new ResponseEntity("no record found ",HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getcontacttrace/{emailId}")
	public ResponseEntity<?> ContactTraceData(@PathVariable("emailId") String emailId) {

		Optional<UpdateContactTraceManually> updateContactTrace =  updateContactTraceManuallyDao.findByFromEmailId(emailId);
		if(updateContactTrace !=null) {
			return new ResponseEntity(updateContactTrace, HttpStatus.OK);
		}
		else {
			return new ResponseEntity("no record found ",HttpStatus.NOT_FOUND);
		}
	}
}
