package com.example.teafproject.springbootmongodbsecurity.service;

import java.util.List;

import com.example.teafproject.springbootmongodbsecurity.domain.BroadCast;


public interface BroadCastService {

	 List<BroadCast> findAll();
}
