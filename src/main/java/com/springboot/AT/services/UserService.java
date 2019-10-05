package com.springboot.AT.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.AT.entity.User;

public interface UserService {
	
	//Create
	/**
	 * Save a user in the database.
	 * @param usuario User to save.
	 * @return Return an object of type User.
	 */
	public User save(User usuario);
	
	//Read
	/**
	 * Search for a user by their identifier in the database.
	 * @param id User identifier.
	 * @return Returns an object of type Optional with the saved user.
	 */
	public Optional<User> findById(Integer id);
	/**
	 * Search all rentals in the database.
	 * @param pageable type object
	 * @return Return a page with all users.
	 */
	public Page<User> findAll(Pageable pageable);
	//Update

	//Remove
	/**
	 * Delete a rent from the database.
	 * @param rent Rent to remove.
	 */
	public void delete(User usuario);
}
