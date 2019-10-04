package com.springboot.AT.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.AT.dto.CarDTO;
import com.springboot.AT.entity.Car;

@Service
public class mapperCarDTOToCar implements MapperService<CarDTO, Car>{

	@Override
	public Car mapperService(CarDTO t) {
		ModelMapper mapper = new ModelMapper();
		Car coche = mapper.map(t, Car.class);
		
		return coche;
	}

}
