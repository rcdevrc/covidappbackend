package com.example.teafproject.springbootmongodbsecurity.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.teafproject.springbootmongodbsecurity.domain.FireBase;
import com.example.teafproject.springbootmongodbsecurity.repository.FireBaseDAO;
import com.example.teafproject.springbootmongodbsecurity.service.FireBaseService;

@Service
public class FireBaseServiceImpl implements FireBaseService {

	private MongoTemplate mongoTemplate;

	@Autowired
	private FireBaseDAO fireBaseDAO;

	@Override
	public FireBase saveFireBaseDetail(FireBase fireBase) {
		fireBaseDAO.save(fireBase);
		return fireBase;
	}
}