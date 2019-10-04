package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.AT.entity.User;


public interface UserService {
	
	//Create
	public User save(User usuario);
	
	//Read
	public Optional<User> findById(Integer i);
	public Page<User> findAll(Pageable pageable);
	//Update
	
	
	//Remove
	public void delete(User usuario);
}
