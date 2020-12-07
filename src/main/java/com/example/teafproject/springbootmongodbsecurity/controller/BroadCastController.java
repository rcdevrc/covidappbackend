package com.example.teafproject.springbootmongodbsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teafproject.springbootmongodbsecurity.domain.BroadCast;
import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;
import com.example.teafproject.springbootmongodbsecurity.repository.BroadCastDAO;
import com.example.teafproject.springbootmongodbsecurity.repository.EmployeeDAO;
import com.example.teafproject.springbootmongodbsecurity.service.impl.BroadCastServiceImpl;
import com.example.teafproject.springbootmongodbsecurity.service.impl.EmployeeDetailServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BroadCastController {
	
	  @Autowired
	  BroadCastDAO broadCastRepository;

	  @Autowired
	  BroadCastServiceImpl broadCastService;
	  
	  @GetMapping("/broadCastList")
	  public ResponseEntity<EmployeeDetail> getEmployeeByEmailId() {
		  
          List<BroadCast> broadCast=broadCastService.findAll();
		    return new ResponseEntity(broadCast, HttpStatus.OK);
		  
		}

}
