package com.example.teafproject.springbootmongodbsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.teafproject.springbootmongodbsecurity.domain.FireBase;


public interface FireBaseDAO extends MongoRepository<FireBase,String>{
	List<FireBase> findByemailId(String emailId);
	Optional<FireBase> findByfireBaseToken(String token);
}
