package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.AT.entity.Car;

public interface CarService {
	//Create
	public Car save(Car coche);
	//Read
	public Optional<Car> findById(Integer id);
	public Page<Car> findAll(Pageable pageable);
	//Update
	/**
	 * Join a car type object with a user type object.
	 * @param idCar id of the car object.
	 * @param idUser id of the user object.
	 * @return Optional<Car> with the join between two objects.
	 */
	public Optional<Car> joinCarWithUser(Integer idCar, Integer idUser);
	//Delete
	public void delete(Car coche);
}
