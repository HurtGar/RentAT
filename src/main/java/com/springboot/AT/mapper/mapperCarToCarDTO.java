package com.springboot.AT.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.AT.dto.CarDTO;
import com.springboot.AT.entity.Car;
@Service
public class mapperCarToCarDTO implements MapperService<Car, CarDTO>{

	@Override
	public CarDTO mapperService(Car t) {
		ModelMapper mapper = new ModelMapper();
		CarDTO cardto = mapper.map(t, CarDTO.class);
		
		return cardto;
	}

	
}
