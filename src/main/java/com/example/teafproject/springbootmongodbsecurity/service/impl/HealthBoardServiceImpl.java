package com.example.teafproject.springbootmongodbsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.teafproject.springbootmongodbsecurity.domain.HealthBoard;
import com.example.teafproject.springbootmongodbsecurity.repository.HealthBoardDAO;
import com.example.teafproject.springbootmongodbsecurity.service.HealthBoardService;
@Service
public class HealthBoardServiceImpl implements HealthBoardService {

	private MongoTemplate mongoTemplate;

	@Autowired
	private HealthBoardDAO healthBoardDAO;

	@Override
	public HealthBoard saveHealthBoardDetail(HealthBoard healthBoard) {
		healthBoardDAO.save(healthBoard);
		return healthBoard;
	}

	
}
