package com.springboot.AT.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.AT.dto.RentDTO;
import com.springboot.AT.entity.Rent;
@Service
public class mapperRentDTOToRent implements MapperService<RentDTO, Rent>{

	@Override
	public Rent mapperService(RentDTO t) {
		ModelMapper mapper = new ModelMapper();
		Rent rent = mapper.map(t, Rent.class);
		
		return rent;
	}
	
}
