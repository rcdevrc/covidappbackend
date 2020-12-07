package com.example.teafproject.springbootmongodbsecurity.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;
import com.example.teafproject.springbootmongodbsecurity.domain.Role;
import com.example.teafproject.springbootmongodbsecurity.repository.EmployeeDAO;
import com.example.teafproject.springbootmongodbsecurity.service.EmployeeDetailService;
@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService {
	
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private EmployeeDAO employeeRepo;
		 
    @Override
    public List<EmployeeDetail> findAll() {
        return employeeRepo.findAll();
    }
    
    @Override
    public EmployeeDetail saveEmployeeDetail(EmployeeDetail employee) {
  	
       employeeRepo.save(employee);
       return employee;
    }
	
    @Override
    public EmployeeDetail findByEmail(String email) {
        EmployeeDetail ed = employeeRepo.findByUserPrincipalName(email);
        return ed!=null?ed:null;
    }
    
    //saveOrUpdateEmployee
    @Override
    public void saveOrUpdateEmployee(EmployeeDetail employee) {
    	
    	employeeRepo.save(employee);
    }
    
    @Override
    public void deleteEmployee(String email) {
    	employeeRepo.deleteById(email);
    }
    
    @Override
    public void deleteAllEmployee() {
        employeeRepo.deleteAll();
    }
    
	public EmployeeDetailServiceImpl() {
    }
}
