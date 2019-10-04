package com.springboot.AT.dto;

import com.springboot.AT.entity.User;
import com.sun.istack.NotNull;

public class CarDTO {
	private Integer idCar;
	@NotNull
	private String marca;
	@NotNull
	private String model;
	
	@NotNull
	private User user;

	public Integer getIdCar() {
		return idCar;
	}

	public void setIdCar(Integer idCar) {
		this.idCar = idCar;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
