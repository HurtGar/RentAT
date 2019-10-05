package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.AT.entity.Car;

public interface CarService {
	//Create
	/**
	 * Save a car.
	 * @param rent Car to save in the DataBase.
	 * @return Returns an object of type car.
	 */
	public Car save(Car coche);
	//Read
	/**
	 * Search for a car by its identifier.
	 * @param id Identifier to search.
	 * @return An Optional object of type car.
	 */
	public Optional<Car> findById(Integer id);
	/**
	 * Search all cars in the database.
	 * @param Pageable type object
	 * @return Return a page with all cars.
	 */
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
	/**
	 * Delete a car from the database.
	 * @param rent Car to remove.
	 */
	public void delete(Car coche);
}
