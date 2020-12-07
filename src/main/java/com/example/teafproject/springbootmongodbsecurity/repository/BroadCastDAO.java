package com.example.teafproject.springbootmongodbsecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.teafproject.springbootmongodbsecurity.domain.BroadCast;
import com.example.teafproject.springbootmongodbsecurity.domain.EmployeeDetail;

public interface BroadCastDAO extends MongoRepository<BroadCast,String>{

}
