package com.springboot.AT.mapper;

public interface MapperService<T, R> {
	/**
	 * Map an object of type R and return an object of type T. 
	 * @param t Object to map.
	 * @return Object of type R
	 */
	R mapperService(T t);
}
