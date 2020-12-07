package com.example.teafproject.springbootmongodbsecurity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;

import java.util.List;

public interface EmployeeDAO extends MongoRepository<EmployeeDetail,String>{
	
    EmployeeDetail findByUserPrincipalName(String email);

}
