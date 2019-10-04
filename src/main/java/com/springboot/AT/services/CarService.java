package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.AT.entity.Car;

import javassist.NotFoundException;

public interface CarService {
	//Create
	public Car save(Car coche);
	//Read
	public Optional<Car> findById(Integer id) throws NotFoundException;
	public Page<Car> findAll(Pageable pageable);
	//Update
	public Optional<Car> joinCarWithUser(Integer idCar, Integer idUser);
	//Delete
	public void delete(Car coche);
}
