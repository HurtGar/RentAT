package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.AT.entity.Rent;

public interface RentService {

	//Create
	public Rent save(Rent rent);
	//Read
	public Optional<Rent> findById(Integer id);
	public Page<Rent> findAll(Pageable pageable);
	//Update --> Relacionar Rent con coche y usuario.
	public Optional<Rent> joinRentWithCarAndUser(Integer idRent, Integer idCar, Integer idUser);
	//Delete
	public void delete(Rent rent);
}
