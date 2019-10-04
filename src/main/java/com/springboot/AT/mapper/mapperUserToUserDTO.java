package com.springboot.AT.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.AT.dto.UserDTO;
import com.springboot.AT.entity.User;
@Service
public class mapperUserToUserDTO implements MapperService<User, UserDTO>{

	@Override
	public UserDTO mapperService(User t) {
		ModelMapper maper = new ModelMapper();
		UserDTO u = maper.map(t, UserDTO.class);
		
		return u;
	}

}
