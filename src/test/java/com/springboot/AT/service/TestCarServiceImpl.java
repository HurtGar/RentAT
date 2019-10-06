package com.springboot.AT.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.AT.entity.Car;
import com.springboot.AT.entity.User;
import com.springboot.AT.repository.CarRepository;
import com.springboot.AT.repository.UserRepository;
import com.springboot.AT.services.CarServiceImpl;

@RunWith(value = MockitoJUnitRunner.class)
public class TestCarServiceImpl {

	@InjectMocks
	private CarServiceImpl carService;

	@Mock
	private CarRepository carRepository;
	@Mock
	private UserRepository userRepository;

	@Test
	public void whenSaveCarGivenACarThenShouldInvokeToSave() {

		// Given
		final Car coche = new Car();
		coche.setMarca("Citroen");
		coche.setModel("C3");

		// When
		carService.save(coche);
		// Then
		Mockito.verify(carRepository).save(coche);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenSaveCarGivenCarThenThrowIllegaArgumentException() {
		// Given
		final Car coche = null;
		// When
		carService.save(coche);
		// Then
	}

	@Test
	public void whenFindByIdRecieveAnIntegerThenShouldReturnsAnOptionalOfTypeCar() {
		// Given
		final Integer id = 1;
		final Car coche = new Car();
		// When
		Mockito.when(carRepository.findById(id)).thenReturn(Optional.ofNullable(coche));
		// Then
		Optional<Car> coche2 = carService.findById(id);

		Assert.assertNotNull(coche2);
		Assert.assertEquals(coche2, Optional.of(coche));
		Assert.assertTrue(coche2.isPresent());
		Assert.assertEquals(coche2.get().getMarca(), coche.getMarca());
		Assert.assertEquals(coche2.get().getModel(), coche.getModel());
	}

	@Test
	public void whenFindByIdRecieveAnIntegerThatDoesntExistsThenShouldReturnError() {
		// Given
		final Integer id = 2342;
		// When
		Mockito.when(carRepository.findById(id)).thenReturn(Optional.empty());
		// Then
		final Optional<Car> coche = carService.findById(id);

		Assert.assertNotNull(coche);
		Assert.assertEquals(coche, Optional.empty());
		Assert.assertFalse(coche.isPresent());
	}

	@Test
	public void whenFindAllPageIsOkThenShouldReturnPageOfCars() {
		// Given
		final List<Car> coches = new ArrayList<>();
		final Page<Car> paginasCars = new PageImpl<>(coches);
		final Pageable pageable = PageRequest.of(0, 10);
		// When
		Mockito.when(carRepository.findAll(pageable)).thenReturn(paginasCars);
		// Then

		final Page<Car> carCoches = carService.findAll(pageable);
		Assert.assertNotNull(carCoches);
		Assert.assertEquals(carCoches, paginasCars);
	}

	@Test
	public void whenJoinCarWithUserRecieveIdCarAndIdUserThenShouldReturnOptionalOfTheJoin() {
		//Given
		final Car coche = new Car();
		final User usuario = new User();
		final Integer idUser = 1;
		final Integer idCar = 1;
		//When
		Mockito.when(carRepository.findById(idCar)).thenReturn(Optional.ofNullable(coche));
		Mockito.when(userRepository.findById(idUser)).thenReturn(Optional.ofNullable(usuario));
		//Then
		Optional<Car> union = carService.joinCarWithUser(idCar, idUser);
		Assert.assertEquals(union.get().getUser(), usuario);
		Assert.assertEquals(union.get().getIdCar(), coche.getIdCar());

	}

	@Test(expected = IllegalArgumentException.class)
	public void whenJoinCarWithUserNotFoundIdCarThenShouldThrowIllegalArgumentException() {
		// Given
		final Integer idCar = 1312;
		// When
		Mockito.when(carRepository.findById(idCar)).thenReturn(Optional.empty());
		// Then
		carService.joinCarWithUser(idCar, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenJoinCarWithUserNotFoundIdUserThenShouldThrowIllegalArgumentException() {
		// Given
		final Integer idCar = 1;
		final Car coche = new Car();
		final Integer idUser = 1312;
		// When
		Mockito.when(carRepository.findById(idCar)).thenReturn(Optional.ofNullable(coche));
		Mockito.when(userRepository.findById(idUser)).thenReturn(Optional.empty());
		// Then
		carService.joinCarWithUser(1, idUser);
	}

	@Test
	public void whenDeleteRecieveAnCarThenShouldRemoveThatCar() {
		final Car coche = new Car();

		carService.delete(coche);

		Mockito.verify(carRepository).delete(coche);
	}
}
