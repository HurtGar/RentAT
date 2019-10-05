package com.springboot.AT.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.AT.dto.RentDTO;
import com.springboot.AT.dto.ResultRent;
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
	private MapperService<Rent, RentDTO> mapperRentTORentDTO;
	@Autowired
	private MapperService<RentDTO, Rent> mapperRentDTOToRent;
	
	//Create
	@PostMapping
	public Rent save(@RequestBody @Valid RentDTO rentDTO) throws ValidationException{
		return rentService.save(mapperRentDTOToRent.mapperService(rentDTO));
	}
	//Read
	@GetMapping("/{id}")
	public RentDTO findById(@PathVariable("id") Integer id) throws NotFoundException{

		return rentService.findById(id)
				.map(mapperRentTORentDTO::mapperService)
				.orElseThrow(()-> new NotFoundException(String.format("Error, no existe usuario con id = %s", id)));
			
	}
	
	@GetMapping
	public Page<Rent> findAllPage(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "10", required = false)Integer size){
		Pageable pageable = PageRequest.of(page, size);
		return rentService.findAll(pageable);
	}
	
	@GetMapping("/carprofit")
	public ResultRent carprofit(@RequestParam(name="idCoche", required = true)Integer id, @RequestParam(name="fechaIni", required = true) @DateTimeFormat(iso =ISO.DATE) LocalDate fechaIni, @RequestParam(name="fechaFin") @DateTimeFormat(iso =ISO.DATE)LocalDate fechaFin) {
		
		return rentService.carProFit(id, fechaIni, fechaFin);
	}
	
	//Update
	@PostMapping("/{idRent}/user/{idUser}/car/{idCar}")
	public RentDTO joinRentWithCarAndUser(@PathVariable("idRent") Integer idRent,@PathVariable("idUser") Integer idUser,@PathVariable("idCar") Integer idCar) {
		Optional<Rent> alquiler = rentService.joinRentWithCarAndUser(idRent, idCar, idUser);
		
		return mapperRentTORentDTO.mapperService(alquiler.get());
	}
	
	@PutMapping("/{idRent}")
	public Rent updateRent(@PathVariable("idRent") Integer idRent, @RequestParam(name = "fechIni", required = true) String inicio, @RequestParam(name= "fechFin", required = true) String fin, @RequestParam(name="price", required = false) Double price) throws NotFoundException {
		
		RentDTO alquiler = rentService.findById(idRent)
				.map(mapperRentTORentDTO::mapperService)
				.orElseThrow(()->new NotFoundException(String.format("Error, no existe el alquiler con id = %s", idRent)));
	
		alquiler.setInitRent(LocalDate.parse(inicio));
		alquiler.setFinalRent(LocalDate.parse(fin));
		alquiler.setPrice(price);
		
		return rentService.save(mapperRentDTOToRent.mapperService(alquiler));
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id)throws NotFoundException {
		rentService.delete(mapperRentDTOToRent
				.mapperService(rentService.findById(id).map(mapperRentTORentDTO::mapperService)
				.orElseThrow(()->new NotFoundException(String.format("Error,alquiler con id = %s no borrado.", id)))));
	}
}