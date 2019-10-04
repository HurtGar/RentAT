package com.springboot.AT.dto;

import com.sun.istack.NotNull;

public class UserDTO {

	private Integer idUser;
	@NotNull
	private String name;
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
