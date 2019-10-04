package com.springboot.AT.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.AT.dto.RentDTO;
import com.springboot.AT.entity.Rent;
import com.springboot.AT.exception.NotFoundException;
import com.springboot.AT.exception.ValidationException;
import com.springboot.AT.mapper.MapperService;
import com.springboot.AT.services.RentService;

@RequestMapping("/rent")
@RestController
public class RentController {

	@Autowired
	private RentService rentService;
	@Autowired
	private MapperService<Rent, RentDTO> mapperCarToCarDTO;
	@Autowired
	private MapperService<RentDTO, Rent> mapperCarDTOToCar;
	
	//Create
	@PostMapping
	public Rent save(@RequestBody @Valid RentDTO rentDTO) throws ValidationException{
		return rentService.save(mapperCarDTOToCar.mapperService(rentDTO));
	}
	//Read
	@GetMapping("/{id}")
	public RentDTO findById(@PathVariable("id") Integer id) throws NotFoundException{

		return rentService.findById(id)
				.map(mapperCarToCarDTO::mapperService)
				.orElseThrow(()-> new NotFoundException(String.format("Error, no existe usuario con id = %s", id)));
			
	}
	
	@GetMapping
	public Page<Rent> findAllPage(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "10", required = false)Integer size){
		Pageable pageable = PageRequest.of(page, size);
		return rentService.findAll(pageable);
	}
	
	//Update
	
	
	//Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id)throws NotFoundException {
		rentService.delete(mapperCarDTOToCar
				.mapperService(rentService.findById(id).map(mapperCarToCarDTO::mapperService)
				.orElseThrow(()->new NotFoundException(String.format("Error,alquiler con id = %s no borrado.", id)))));
	}
}