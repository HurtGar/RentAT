package com.springboot.AT.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.AT.dto.UserDTO;
import com.springboot.AT.entity.User;
import com.springboot.AT.exception.NotFoundException;
import com.springboot.AT.mapper.MapperService;
import com.springboot.AT.services.UserService;

@RequestMapping(value="/user")
@RestController
public class UserController {

	//Inyecciones
	@Autowired
	private UserService userService;
	@Autowired
	private MapperService<User, UserDTO> mapperUserToUserDTO;
	@Autowired
	private MapperService<UserDTO, User> mapperUserDTOToUser;
	
	//Create
	@PostMapping
	public User save(@RequestBody @Valid UserDTO userDTO) {
		return userService.save(mapperUserDTOToUser.mapperService(userDTO));
	}
	
	//Read
	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable("id") Integer id) throws NotFoundException{

		return userService.findById(id)
				.map(mapperUserToUserDTO::mapperService)
				.orElseThrow(()-> new NotFoundException(String.format("Error, no existe usuario con id = %s", id)));
			
	}
	@GetMapping
	public Page<User> findAllPage(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "10", required = false)Integer size){
		Pageable pageable = PageRequest.of(page, size);
		return userService.findAll(pageable);
	}
	
	//Update
	@PutMapping("/{id}")
	public User update(@PathParam("id") Integer id, @RequestBody UserDTO usuarioDTO) throws NotFoundException{
		UserDTO usuario = userService.findById(id)
				.map(mapperUserToUserDTO::mapperService)
				.orElseThrow(()-> new NotFoundException(String.format("Error, id = %s", id)));
		
		usuario.setName(usuarioDTO.getName());
		
		return userService.save(mapperUserDTOToUser.mapperService(usuario));
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException {
		userService.delete(mapperUserDTOToUser.mapperService(userService.findById(id)
				.map(mapperUserToUserDTO::mapperService).orElseThrow(()-> new NotFoundException(String.format("Error,usuario con  id = %s no borrado.", id)))));
	}
}
