package com.springboot.AT.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
*/

@Entity
@Table(name = "alquileres")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rent")
	private Integer idRent;
	
	@Column(name = "inicio_alquiler")
	private LocalDate initRent;
	@Column(name = "fin_alquiler")
	private LocalDate finalRent;
	@Column(name = "precio")
	private Double price;
	
	//@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	//@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
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
