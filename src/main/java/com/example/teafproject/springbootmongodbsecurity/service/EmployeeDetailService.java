package com.example.teafproject.springbootmongodbsecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;


public interface EmployeeDetailService {
	 List<EmployeeDetail> findAll();
	 
	 EmployeeDetail saveEmployeeDetail(EmployeeDetail employee);
	 
	 EmployeeDetail findByEmail(String email);
	
	 void saveOrUpdateEmployee(EmployeeDetail employee);
	 
	 void deleteEmployee(String email);
	 
	 void deleteAllEmployee();
}
