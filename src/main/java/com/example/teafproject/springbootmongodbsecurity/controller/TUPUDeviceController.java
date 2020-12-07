package com.example.teafproject.springbootmongodbsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teafproject.springbootmongodbsecurity.domain.TupuDevice;
import com.example.teafproject.springbootmongodbsecurity.repository.TupuDeviceDAO;
import com.example.teafproject.springbootmongodbsecurity.service.impl.TupuDeviceServiceImpl;
import com.example.teafproject.springbootmongodbsecurity.util.Utility;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tupu")
public class TUPUDeviceController {
	
	  @Autowired
	  TupuDeviceDAO tupuDao;
	  
	  @Autowired
	  Utility util;

	  @Autowired
      TupuDeviceServiceImpl tupuService;
	  
	@PostMapping(value = "/gettupudata")
	public ResponseEntity<?> saveOrUpdateTupudata(@RequestBody String strTupuDevice) {
		TupuDevice tupuDevice=util.processJson(strTupuDevice);
		tupuDao.save(tupuDevice);
		return new ResponseEntity("Device data saved successfully", HttpStatus.OK);
	}
}
