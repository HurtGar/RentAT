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

import com.springboot.AT.entity.User;
import com.springboot.AT.repository.UserRepository;
import com.springboot.AT.services.UserServiceImpl;

@RunWith(value = MockitoJUnitRunner.class)
public class TestUserServiceImpl {
	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void whenSaveUserGivenAUserThenShouldInvokeToSave() {

		// Given
		final User usuario = new User();
		usuario.setName("Adrian");

		// When
		userService.save(usuario);
		// Then
		Mockito.verify(userRepository).save(usuario);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenSaveUserGivenUserThenThrowIllegaArgumentException() {
		// Given
		final User usuario = null;
		// When
		userService.save(usuario);
		// Then
	}

	@Test
	public void whenFindByIdRecieveAnIntegerThenShouldReturnsAnOptionalOfTypeUser() {
		// Given
		final Integer id = 1;
		final User usuario = new User();
		// When
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(usuario));
		// Then
		Optional<User> usuario2 = userService.findById(id);

		Assert.assertNotNull(usuario2);
		Assert.assertEquals(usuario2, Optional.of(usuario));
		Assert.assertTrue(usuario2.isPresent());
		Assert.assertEquals(usuario2.get().getName(), usuario.getName());
	}

	@Test
	public void whenFindByIdRecieveAnIntegerThatDoesntExistsThenShouldReturnError() {
		// Given
		final Integer id = 2342;
		// When
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		// Then
		final Optional<User> usuario = userService.findById(id);

		Assert.assertNotNull(usuario);
		Assert.assertEquals(usuario, Optional.empty());
		Assert.assertFalse(usuario.isPresent());
	}
	
	@Test
	public void whenFindAllPageIsOkThenShouldReturnPageOfBooks() {
		//Given
		final List<User> usuarios = new ArrayList<>();
		final Page<User> paginasUsers = new PageImpl<>(usuarios);
		final Pageable pageable = PageRequest.of(0, 10);
		//When
		Mockito.when(userRepository.findAll(pageable)).thenReturn(paginasUsers);
		//Then
		
		final Page<User> usuariosUser = userService.findAll(pageable);
		Assert.assertNotNull(usuariosUser);
		Assert.assertEquals(usuariosUser, paginasUsers);
	}

	@Test
	public void whenDeleteRecieveAnUserThenShouldRemoveThatUser() {
		final User usuario = new User();
		
		userService.delete(usuario);
		
		Mockito.verify(userRepository).delete(usuario);
	}
}
