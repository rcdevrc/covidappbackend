package com.example.teafproject.springbootmongodbsecurity.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teafproject.springbootmongodbsecurity.domain.BroadCast;
import com.example.teafproject.springbootmongodbsecurity.domain.FireBase;
import com.example.teafproject.springbootmongodbsecurity.repository.BroadCastDAO;
import com.example.teafproject.springbootmongodbsecurity.repository.FireBaseDAO;
import com.example.teafproject.springbootmongodbsecurity.service.impl.FireBaseServiceImpl;
import com.example.teafproject.springbootmongodbsecurity.util.Utility;

@RestController
@RequestMapping("/api")
public class FireBaseController {

	@Autowired
	FireBaseDAO fireBaseDao;

	@Autowired
	FireBaseServiceImpl fireBaseService;

	@Autowired
	Utility util;
		
	@Autowired
	BroadCastDAO broadCastDAO;

	@PostMapping(value = "/savefirebasetoken")
	public ResponseEntity<?> saveFireBaseData(@RequestBody FireBase fireBase) {
		fireBaseDao.save(fireBase);
		return new ResponseEntity("Firebase data saved successfully", HttpStatus.OK);
	}

	@GetMapping(value = "/getfirebasetokenbyemail/{emailId}")
	public ResponseEntity<?> FireBaseDataByEmail(@PathVariable("emailId") String emailId) {

		List<FireBase> fireBase = fireBaseDao.findByemailId(emailId);
		if (fireBase != null) {
			return new ResponseEntity(fireBase, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getfirebasetokens/{token}")
	public ResponseEntity<?> FireBaseDataByToken(@PathVariable("token") String token) {

		Optional<FireBase> fireBase = fireBaseDao.findByfireBaseToken(token);
		if (fireBase != null) {
			return new ResponseEntity(fireBase, HttpStatus.OK);
		} else {
			return new ResponseEntity("no record found ", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getallfirebasetokens")
	public ResponseEntity<?> FireBaseAllTokens() {

		List<FireBase> fireBase = fireBaseDao.findAll();
		if (fireBase != null) {
			return new ResponseEntity(fireBase, HttpStatus.OK);
		} else {
			return new ResponseEntity("no record found ", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path = "sendMessage/{title}/{message}/{option}")
	public void sendFireBaseNotifications(@PathVariable("title") String title, @PathVariable("message") String message,
		@PathVariable("option") String option) {
		
		
		List<String> tokens = new ArrayList<String>();
		List<FireBase> fireBase = fireBaseDao.findAll();
		if (fireBase != null) {

			for (int index = 0; index < fireBase.size(); index++) {

				tokens.add(fireBase.get(index).getFireBaseToken());
			}

		}
		for (int i = 0; i < tokens.size(); i++)
			try {
				util.pushFCMNotification(tokens.get(i), title, message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * broadcasting the the selected email list
	 * 
	 * 
	 * @param BroadCast
	 *            broadCast
	 */
	@PostMapping(value="/sendMessage")
	public ResponseEntity<?> sendFireBaseNotifications(@RequestBody BroadCast broadCast) {
			
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime dateTime  = LocalDateTime.now();  
		String formattedDateTime = dateTime.format(dtf);
		
		broadCast.setSender("Admin");
		broadCast.setStatus("Sent");
		broadCast.setCreateDate(formattedDateTime);
		
		broadCastDAO.save(broadCast);
		
		
		 try { for (String email : broadCast.getEmailIds()) { List<FireBase> fireBase
		  = fireBaseDao.findByemailId(email); if (fireBase != null) { for (int i = 0; i
		  < fireBase.size(); i++) {
		 
		  util.pushFCMNotification(fireBase.get(i).getFireBaseToken(),
		 broadCast.getTitle(), broadCast.getMessage()); } }
		  
		  } return new ResponseEntity("", HttpStatus.OK); } catch (Exception e) {
		  
		  e.printStackTrace(); } return new ResponseEntity("",
		  HttpStatus.FAILED_DEPENDENCY);
		 
		
	}
}
