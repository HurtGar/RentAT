package com.springboot.AT.dto;

import java.time.LocalDate;

import com.sun.istack.NotNull;

public class ResultRent {

	@NotNull
	private String title;
	@NotNull
	private LocalDate initDate;
	@NotNull
	private LocalDate finalDate;
	@NotNull
	private Double price;
	
	public ResultRent() {}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getInitDate() {
		return initDate;
	}
	public void setInitDate(LocalDate initDate) {
		this.initDate = initDate;
	}
	public LocalDate getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
