package com.springboot.AT.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.AT.dto.RentDTO;
import com.springboot.AT.entity.Rent;
@Service
public class mapperRentTORentDTO implements MapperService<Rent, RentDTO>{

	@Override
	public RentDTO mapperService(Rent t) {
		ModelMapper mapper = new ModelMapper();
		RentDTO rent = mapper.map(t, RentDTO.class);
		
		return rent;
	}
	
}
