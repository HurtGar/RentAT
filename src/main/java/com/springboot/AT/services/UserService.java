package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.AT.entity.User;

import javassist.NotFoundException;


public interface UserService {
	
	//Create
	public User save(User usuario);
	
	//Read
	public Optional<User> findById(Integer id) throws NotFoundException;
	public Page<User> findAll(Pageable pageable);
	//Update
	
	
	//Remove
	public void delete(User usuario);
}
