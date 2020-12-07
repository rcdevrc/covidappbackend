package com.example.teafproject.springbootmongodbsecurity.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.teafproject.springbootmongodbsecurity.domain.UpdateContactTraceManually;

public interface UpdateContactTraceManuallyDAO extends MongoRepository<UpdateContactTraceManually,String>{

	Optional<UpdateContactTraceManually> findByFromEmailId(String emailId);

	

}