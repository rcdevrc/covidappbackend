package com.example.teafproject.springbootmongodbsecurity.controller;

import java.util.List;

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

import com.example.teafproject.springbootmongodbsecurity.domain.HealthBoard;
import com.example.teafproject.springbootmongodbsecurity.repository.HealthBoardDAO;
import com.example.teafproject.springbootmongodbsecurity.service.impl.HealthBoardServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HealthBoardController {

	@Autowired
	HealthBoardDAO healthBoardDao;

	@Autowired
	HealthBoardServiceImpl healthBoardService;

	@PostMapping(value = "/healthboard")
	public ResponseEntity<?> saveHealthBoardData(@RequestBody HealthBoard healthBoard) {
		healthBoardDao.save(healthBoard);
		return new ResponseEntity("HealthBoard data saved successfully", HttpStatus.OK);
	}

	@GetMapping(value = "/gethealthboard/{status}")
	public ResponseEntity<HealthBoard> HealthBoardData(@PathVariable("status") String status) {

		List<HealthBoard> healthBoardList =  healthBoardDao.findByStatus(status);
		if(healthBoardList !=null) {
			return new ResponseEntity(healthBoardList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity("no record found ",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/getviewmanageall")
	public ResponseEntity<HealthBoard> HealthBoardAllData() {

		List<HealthBoard> healthBoardList =  healthBoardDao.findAll();
		if(healthBoardList !=null) {
			return new ResponseEntity(healthBoardList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity("no record found ",HttpStatus.NOT_FOUND);
		}
	}
}
