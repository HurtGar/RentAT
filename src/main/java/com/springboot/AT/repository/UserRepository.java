package com.springboot.AT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.AT.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
