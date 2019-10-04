package com.springboot.AT.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Exception NotFound(NotFoundException exception) {
		return exception;
	}
	
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Exception validationException(ValidationException exception) {
		return exception;
	}
	
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Exception illegalArgument(IllegalArgumentException exception) {
		return exception;
	}
}
