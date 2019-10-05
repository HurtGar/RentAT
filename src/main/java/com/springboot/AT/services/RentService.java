package com.springboot.AT.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.AT.dto.ResultRent;
import com.springboot.AT.entity.Rent;


public interface RentService {

	//Create
	/**
	 * Save a rent.
	 * @param rent Rent to save in the DataBase.
	 * @return Returns an object of type rent.
	 */
	public Rent save(Rent rent);
	//Read
	/**
	 * Search for a rental by its identifier.
	 * @param id Identifier to search.
	 * @return An Optional object of type rent.
	 */
	public Optional<Rent> findById(Integer id);
	/**
	 * Search all rentals in the database.
	 * @param Pageable type object
	 * @return Return a page with all rentals.
	 */
	public Page<Rent> findAll(Pageable pageable);
	/**
	 * Calculate the total profit of a car between two given dates.
	 * @param idCar Car identifier.
	 * @param inicio Start date.
	 * @param fin End date.
	 * @return Returns an object of type ResultRent.
	 */
	public ResultRent carProFit(Integer idCar, LocalDate inicio, LocalDate fin);
	//Update --> Relacionar Rent con coche y usuario.
	/**
	 * Match a rental with a user and a car.
	 * @param idRent Rental identifier.
	 * @param idCar Car identifier.
	 * @param idUser User identifier.
	 * @return Returns an object of type Optional with the rent.
	 */
	public Optional<Rent> joinRentWithCarAndUser(Integer idRent, Integer idCar, Integer idUser);
	//Delete
	/**
	 * Delete a rent from the database.
	 * @param rent Rent to remove.
	 */
	public void delete(Rent rent);
}
