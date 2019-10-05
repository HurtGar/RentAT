package com.springboot.AT.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.AT.dto.ResultRent;
import com.springboot.AT.entity.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {
	
	@Query(value = "SELECT SUM(price) FROM Rent r WHERE r.car = :idCar AND r.finalRent between ':ini' AND ':fin'")
	public Rent carProfit(@Param(value = "idCar") Integer idCar, @Param(value="ini") LocalDate fechaIn,@Param(value="fin") LocalDate fechaFin);
}
