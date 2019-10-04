package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.AT.entity.User;
import com.springboot.AT.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("User is empty.");
		}
		
		userRepository.save(usuario);
		return usuario;
	}

	@Override
	public Optional<User> findById(Integer id){
		Optional<User> usuario = userRepository.findById(id);
		if(!usuario.isPresent()) {
			//throw new NotFoundException("Usuario no encontrado.");
			System.err.println("User not found.");
		}
		
		return usuario;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public void delete(User usuario) {
		userRepository.delete(usuario);

	}

}
