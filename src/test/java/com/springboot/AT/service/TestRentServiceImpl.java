package com.springboot.AT.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
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

import com.springboot.AT.dto.ResultRent;
import com.springboot.AT.entity.Car;
import com.springboot.AT.entity.Rent;
import com.springboot.AT.entity.User;
import com.springboot.AT.repository.CarRepository;
import com.springboot.AT.repository.RentRepository;
import com.springboot.AT.repository.UserRepository;
import com.springboot.AT.services.CarServiceImpl;
import com.springboot.AT.services.RentServiceImpl;

@RunWith(value = MockitoJUnitRunner.class)
public class TestRentServiceImpl {

	@InjectMocks
	private RentServiceImpl rentService;

	@InjectMocks
	private CarServiceImpl carService;

	@Mock
	private RentRepository rentRepository;

	@Mock
	private CarRepository carRepository;
	
	@Mock 
	private UserRepository userRepository;

	/**
	 * Test de la función carProFit que se nos pedía realizar el test.
	 */
	@Test
	public void whenCarProFitReceivesAIdCarAndStartDateAndEndDateThenShouldThrowAResultRent() {
		// Given
		final Car coche = new Car();
		final Integer idCar = 1;
		final LocalDate inicio = LocalDate.now();
		final LocalDate fin = LocalDate.now();
		
		// When
		Mockito.when(carRepository.findById(idCar)).thenReturn(Optional.ofNullable(coche));
		
		// Then
		final Optional<Car> cocheService = carService.findById(idCar);
		Assert.assertNotNull(cocheService);
		final ResultRent resultado = rentService.carProFit(idCar, inicio, fin);
		Assert.assertNotNull(resultado);
		Assert.assertEquals(resultado.getTitle(), "Marca: "+coche.getMarca()+", Modelo: "+coche.getModel());
		Assert.assertEquals(resultado.getInitDate(), inicio);
		Assert.assertEquals(resultado.getFinalDate(), fin);
	}

	
	
	@Test
	public void whenSaveRentGivenARentThenShouldInvokeToSave() {

		// Given
		final Rent alquiler = new Rent();
		alquiler.setPrice(1234.00);
		alquiler.setInitRent(LocalDate.now());
		alquiler.setFinalRent(LocalDate.now());

		// When
		rentService.save(alquiler);
		// Then
		Mockito.verify(rentRepository).save(alquiler);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenSaveRentGivenRentThenThrowIllegaArgumentException() {
		// Given
		final Rent alquiler = null;
		// When
		rentService.save(alquiler);
		// Then
	}

	@Test
	public void whenFindByIdRecieveAnIntegerThenShouldReturnsAnOptionalOfTypeRent() {
		// Given
		final Integer id = 1;
		final Rent alquiler = new Rent();
		// When
		Mockito.when(rentRepository.findById(id)).thenReturn(Optional.ofNullable(alquiler));
		// Then
		Optional<Rent> alquiler2 = rentService.findById(id);

		Assert.assertNotNull(alquiler2);
		Assert.assertEquals(alquiler2, Optional.of(alquiler));
		Assert.assertTrue(alquiler2.isPresent());
		Assert.assertEquals(alquiler2.get().getFinalRent(), alquiler.getFinalRent());
		Assert.assertEquals(alquiler2.get().getPrice(), alquiler.getPrice());
	}

	@Test
	public void whenFindByIdRecieveAnIntegerThatDoesntExistsThenShouldReturnError() {
		// Given
		final Integer id = 2342;
		// When
		Mockito.when(rentRepository.findById(id)).thenReturn(Optional.empty());
		// Then
		final Optional<Rent> alquiler = rentService.findById(id);

		Assert.assertNotNull(alquiler);
		Assert.assertEquals(alquiler, Optional.empty());
		Assert.assertFalse(alquiler.isPresent());
	}

	@Test
	public void whenFindAllPageIsOkThenShouldReturnPageOfRents() {
		// Given
		final List<Rent> alquileres = new ArrayList<>();
		final Page<Rent> paginasAlquiler = new PageImpl<>(alquileres);
		final Pageable pageable = PageRequest.of(0, 10);
		// When
		Mockito.when(rentRepository.findAll(pageable)).thenReturn(paginasAlquiler);
		// Then

		final Page<Rent> rentAlq = rentService.findAll(pageable);
		Assert.assertNotNull(rentAlq);
		Assert.assertEquals(rentAlq, paginasAlquiler);
	}

	@Test
	public void whenDeleteRecieveAnCarThenShouldRemoveThatCar() {

		// Given
		final Rent alquiler = new Rent();

		// When
		rentService.delete(alquiler);

		// Then
		Mockito.verify(rentRepository).delete(alquiler);
	}

	@Test
	public void whenJoinRentWithCarAndUserGivenIdRentIdCarIdUserThenReturnOptionalRent() {
		// Given
		final Rent alquiler = new Rent();
		final Car coche = new Car();
		final User usuario = new User();
		final Integer idCar = 1;
		final Integer idRent = 1;
		final Integer idUser = 1;
		// When
		Mockito.when(rentRepository.findById(idRent)).thenReturn(Optional.ofNullable(alquiler));
		Mockito.when(carRepository.findById(idCar)).thenReturn(Optional.ofNullable(coche));
		Mockito.when(userRepository.findById(idUser)).thenReturn(Optional.ofNullable(usuario));
		// Then
		final Optional<Rent> union = rentService.joinRentWithCarAndUser(idRent, idCar, idUser);
		
		Assert.assertNotNull(alquiler);
		Assert.assertNotNull(coche);
		Assert.assertNotNull(usuario);
		Assert.assertNotNull(union);
		Assert.assertEquals(union.get().getCar(), coche);
		Assert.assertEquals(union.get().getUser(), usuario);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenJoinRentWithCarAndUserGivenIdCarIdUserButIdRentNullThenReturnOptionalRent() {
		// Given
		final Integer idCar = 1;
		final Integer idRent = 434;
		final Integer idUser = 1;
		// When
		Mockito.when(rentRepository.findById(idRent)).thenReturn(Optional.empty());
	
		// Then
		rentService.joinRentWithCarAndUser(idRent, idCar, idUser);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenJoinRentWithCarAndUserGivenIdRentIdCarButIdUserNullThenReturnOptionalRent() {
		// Given
		final Rent alquiler = new Rent();
		final Integer idCar = 1;
		final Integer idRent = 1;
		final Integer idUser = 1434;
		// When
		Mockito.when(rentRepository.findById(idRent)).thenReturn(Optional.ofNullable(alquiler));
		Mockito.when(userRepository.findById(idUser)).thenReturn(Optional.empty());
	
		// Then
		rentService.joinRentWithCarAndUser(idRent, idCar, idUser);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenJoinRentWithCarAndUserGivenIdRentIdUserButIdCarNullThenReturnOptionalRent() {
		// Given
		final Rent alquiler = new Rent();
		final User usuario = new User();
		final Integer idCar = 13434;
		final Integer idRent = 1;
		final Integer idUser = 1;
		// When
		Mockito.when(rentRepository.findById(idRent)).thenReturn(Optional.ofNullable(alquiler));
		Mockito.when(userRepository.findById(idUser)).thenReturn(Optional.ofNullable(usuario));
		Mockito.when(carRepository.findById(idCar)).thenReturn(Optional.empty());
	
		// Then
		rentService.joinRentWithCarAndUser(idRent, idCar, idUser);
		
	}
	
	
}
