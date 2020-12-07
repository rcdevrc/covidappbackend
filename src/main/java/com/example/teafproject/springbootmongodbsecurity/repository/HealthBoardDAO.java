package com.example.teafproject.springbootmongodbsecurity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.teafproject.springbootmongodbsecurity.domain.HealthBoard;

public interface HealthBoardDAO extends MongoRepository<HealthBoard,String>{

	List<HealthBoard> findByStatus(String status);

}