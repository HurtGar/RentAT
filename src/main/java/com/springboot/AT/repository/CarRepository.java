package com.springboot.AT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.AT.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
