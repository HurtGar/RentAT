package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.AT.entity.Car;
import com.springboot.AT.entity.User;
import com.springboot.AT.repository.CarRepository;
import com.springboot.AT.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class CarServiceImpl implements CarService{

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Car save(Car coche) {
		if(coche == null) {
			throw new IllegalArgumentException("Error, coche is null.");
		}
		
		return carRepository.save(coche);
	}

	@Override
	public Optional<Car> findById(Integer id) throws NotFoundException {
		Optional<Car> coche = carRepository.findById(id);
		if(!coche.isPresent()) {
			throw new NotFoundException("Error, car is empty.");
		}
		
		return coche;
	}

	@Override
	public Page<Car> findAll(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

	@Override
	public Optional<Car> joinCarWithUser(Integer idCar, Integer idUser) {
		Optional<Car> coche = carRepository.findById(idCar);
		Optional<User> usuario = userRepository.findById(idUser);
		
		if(coche.isPresent()) {
			if(usuario.isPresent()) {
				coche.get().setUser(usuario.get());
				carRepository.save(coche.get());
				return coche;
			}else {
				throw new IllegalArgumentException("Usuario no encontrado.");
			}
		}else {
			throw new IllegalArgumentException("Coche no encontrado.");
		}
	}

	@Override
	public void delete(Car coche) {
		carRepository.delete(coche);
		
	}
}
