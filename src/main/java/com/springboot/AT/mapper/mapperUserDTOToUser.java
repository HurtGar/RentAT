package com.springboot.AT.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.AT.dto.UserDTO;
import com.springboot.AT.entity.User;
@Service
public class mapperUserDTOToUser implements MapperService<UserDTO, User> {

	@Override
	public User mapperService(UserDTO t) {
		ModelMapper mapper = new ModelMapper();
		User usuario = mapper.map(t, User.class);
		
		return usuario;
	}
	
}
