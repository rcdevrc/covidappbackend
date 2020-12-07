package com.example.teafproject.springbootmongodbsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.teafproject.springbootmongodbsecurity.domain.TupuDevice;
import com.example.teafproject.springbootmongodbsecurity.repository.TupuDeviceDAO;
import com.example.teafproject.springbootmongodbsecurity.service.TUPUDeviceService;
@Service
public class TupuDeviceServiceImpl implements TUPUDeviceService {

	private MongoTemplate mongoTemplate;

	@Autowired
	private TupuDeviceDAO tupuDAO;

	@Override
	public TupuDevice saveTupuDeviceEmployeeDetail(TupuDevice tupuDevice) {
		tupuDAO.save(tupuDevice);
		return tupuDevice;
	}
}
