package com.springboot.AT.dto;

import java.time.LocalDate;

import com.springboot.AT.entity.Car;
import com.springboot.AT.entity.User;
import com.sun.istack.NotNull;

public class RentDTO {

	private Integer idRent;
	@NotNull
	private LocalDate initRent;
	@NotNull
	private LocalDate finalRent;
	@NotNull
	private Double price;
	@NotNull
	private User user;
	@NotNull
	private Car car;
	public Integer getIdRent() {
		return idRent;
	}
	public void setIdRent(Integer idRent) {
		this.idRent = idRent;
	}
	public LocalDate getInitRent() {
		return initRent;
	}
	public void setInitRent(LocalDate initRent) {
		this.initRent = initRent;
	}
	public LocalDate getFinalRent() {
		return finalRent;
	}
	public void setFinalRent(LocalDate finalRent) {
		this.finalRent = finalRent;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
