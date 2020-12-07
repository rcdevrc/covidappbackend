package com.example.teafproject.springbootmongodbsecurity.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.teafproject.springbootmongodbsecurity.domain.TupuDevice;

public interface TupuDeviceDAO extends MongoRepository<TupuDevice,String>{

	Optional<TupuDevice> findBypersonId(String email);

}