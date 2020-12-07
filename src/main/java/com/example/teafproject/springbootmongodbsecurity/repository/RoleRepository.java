package com.example.teafproject.springbootmongodbsecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.teafproject.springbootmongodbsecurity.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	Role findByRole(String role);
}
