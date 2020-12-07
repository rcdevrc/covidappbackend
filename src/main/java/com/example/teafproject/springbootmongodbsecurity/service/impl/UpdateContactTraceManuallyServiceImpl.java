package com.example.teafproject.springbootmongodbsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.teafproject.springbootmongodbsecurity.domain.UpdateContactTraceManually;
import com.example.teafproject.springbootmongodbsecurity.repository.UpdateContactTraceManuallyDAO;
import com.example.teafproject.springbootmongodbsecurity.service.UpdateContactTraceManuallyService;
@Service
public class UpdateContactTraceManuallyServiceImpl implements UpdateContactTraceManuallyService {

	private MongoTemplate mongoTemplate;

	@Autowired
	private UpdateContactTraceManuallyDAO updateContactTraceManuallyDAO;

	@Override
	public UpdateContactTraceManually saveContactTraceDetail(UpdateContactTraceManually updateContactTraceManually) {
		updateContactTraceManuallyDAO.save(updateContactTraceManually);
		return updateContactTraceManually;
	}
}
