package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.AT.entity.Car;
import com.springboot.AT.entity.Rent;
import com.springboot.AT.entity.User;
import com.springboot.AT.repository.CarRepository;
import com.springboot.AT.repository.RentRepository;
import com.springboot.AT.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private RentRepository rentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CarRepository carRepository;

	@Override
	public Rent save(Rent rent) {
		if (rent == null) {
			throw new IllegalArgumentException("Error, rent doesn't exists.");
		}

		return rentRepository.save(rent);
	}

	@Override
	public Optional<Rent> findById(Integer id) throws NotFoundException {
		Optional<Rent> alquiler = rentRepository.findById(id);
		if (!alquiler.isPresent()) {
			throw new NotFoundException("Error, rent is empty.");
		}
		return alquiler;
	}

	@Override
	public Page<Rent> findAll(Pageable pageable) {
		return rentRepository.findAll(pageable);
	}

	@Override
	public Optional<Rent> joinRentWithCarAndUser(Integer idRent, Integer idCar, Integer idUser) {
		Optional<Rent> alquiler = rentRepository.findById(idRent);
		Optional<User> usuario = userRepository.findById(idUser);
		Optional<Car> coche = carRepository.findById(idCar);

		if (alquiler.isPresent()) {

			if (usuario.isPresent()) {

				if (coche.isPresent()) {
					alquiler.get().setCar(coche.get());
					alquiler.get().setUser(usuario.get());
					rentRepository.save(alquiler.get());
					return alquiler;
				} else {
					throw new IllegalArgumentException("Error, car doesn't exists.");
				}
			} else {
				throw new IllegalArgumentException("Error, user doesn't exists.");
			}
		} else {
			throw new IllegalArgumentException("Error, rent doesn't exists.");
		}
	}

	@Override
	public void delete(Rent rent) {
		rentRepository.delete(rent);

	}

}
