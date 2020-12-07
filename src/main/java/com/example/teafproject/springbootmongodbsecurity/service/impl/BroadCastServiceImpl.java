package com.example.teafproject.springbootmongodbsecurity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.example.teafproject.springbootmongodbsecurity.domain.BroadCast;
import com.example.teafproject.springbootmongodbsecurity.repository.BroadCastDAO;
import com.example.teafproject.springbootmongodbsecurity.service.BroadCastService;

@Component
public class BroadCastServiceImpl  implements BroadCastService{
	
private MongoTemplate mongoTemplate;
	
	@Autowired
	private BroadCastDAO broadCastRepo;

	@Override
	public List<BroadCast> findAll() {		
		return broadCastRepo.findAll();
	}

}
