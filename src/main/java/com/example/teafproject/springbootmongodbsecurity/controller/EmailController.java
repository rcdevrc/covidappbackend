package com.example.teafproject.springbootmongodbsecurity.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;
import com.example.teafproject.springbootmongodbsecurity.service.MailService;



public class EmailController {
	
	@Autowired
	private MailService notificationService;

	@Autowired
	private EmployeeDetail employee;
	
	public String send() {

		/*
		 * Creating a User with the help of User class that we have declared and setting
		 * Email address of the sender.
		 */
		/*
		 * Here we will call sendEmail() for Sending mail to the sender.
		 */
		try {
			notificationService.sendEmail(employee);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Mail Sent";
	}

}

