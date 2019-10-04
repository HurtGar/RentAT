package com.springboot.AT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.AT.entity.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {

}
