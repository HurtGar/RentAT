package com.springboot.AT.repository;


import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.springboot.AT.entity.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {
	
	/**
	 * Función para obtener el beneficio de un coche entre dos fechas.
	 * @param idCar Identificador del coche a buscar.
	 * @param string Fecha de inicio de búsqueda.
	 * @param string2 Fecha de fin de búsqueda.
	 * @return Devuelve un objeto
	 */
	@Query(value = "SELECT SUM(price) FROM Rent AS r  WHERE r.car.idCar = :idCar AND r.finalRent between :ini AND :fin")
	public Double carProfit(@Param(value = "idCar") Integer idCar, @Param(value="ini") LocalDate ini, @Param(value="fin") LocalDate fin);
}
