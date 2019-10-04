package com.springboot.AT.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.AT.dto.CarDTO;
import com.springboot.AT.dto.UserDTO;
import com.springboot.AT.entity.Car;
import com.springboot.AT.entity.User;
import com.springboot.AT.exception.NotFoundException;
import com.springboot.AT.exception.ValidationException;
import com.springboot.AT.mapper.MapperService;
import com.springboot.AT.services.CarService;

@RequestMapping(value = "/car")
@RestController
public class CarController {

	@Autowired
	private CarService carService;
	@Autowired
	private MapperService<Car, CarDTO> mapperCarToCarDTO;
	@Autowired
	private MapperService<CarDTO, Car> mapperCarDTOToCar;
	@Autowired
	private MapperService<User, UserDTO> mapperUserToUserDTO;
	

	// Create
	@PostMapping
	public Car save(@RequestBody @Valid CarDTO carDTO) throws ValidationException {
		return carService.save(mapperCarDTOToCar.mapperService(carDTO));
	}
	@PostMapping("/{idCoche}/user/{idUser}")
	public CarDTO joinCarWithUser(@PathVariable("idCoche") Integer idCar, @PathVariable("idUser") Integer idUser) throws NotFoundException{
		Optional<Car> coche = carService.joinCarWithUser(idCar, idUser);
		
		return mapperCarToCarDTO.mapperService(coche.get());
		
	}
	// Read
	@GetMapping("/{id}")
	public CarDTO findById(@PathVariable("id") Integer id) throws NotFoundException {
		return carService.findById(id).map(mapperCarToCarDTO::mapperService)
				.orElseThrow(() -> new NotFoundException(String.format("Error, no existe coche con id = %s", id)));
	}
	
	@GetMapping("/{idCoche}/user")
	public UserDTO findUserByIdCoche(@PathVariable("idCoche") Integer id) throws NotFoundException{
		CarDTO coche = carService.findById(id).map(mapperCarToCarDTO::mapperService)
				.orElseThrow(() -> new NotFoundException(String.format("Error, no existe coche con id = %s", id)));
		
		UserDTO usuario = mapperUserToUserDTO.mapperService(coche.getUser());
		return usuario;
	}

	@GetMapping
	public Page<Car> findAllPage(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return carService.findAll(pageable);
	}

	// Update
	@PutMapping("/{id}")
	public Car update(@PathParam("id") Integer id, @RequestBody CarDTO cocheDTO) throws NotFoundException {
		CarDTO coche = carService.findById(id).map(mapperCarToCarDTO::mapperService)
				.orElseThrow(() -> new NotFoundException(String.format("Error, id = %s", id)));

		coche.setMarca(cocheDTO.getMarca());
		coche.setModel(cocheDTO.getModel());

		return carService.save(mapperCarDTOToCar.mapperService(coche));
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException {
		carService.delete(mapperCarDTOToCar.mapperService(carService.findById(id).map(mapperCarToCarDTO::mapperService)
				.orElseThrow(() -> new NotFoundException(String.format("Error, id = %s", id)))));
	}
}
