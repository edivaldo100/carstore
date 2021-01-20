package com.edivaldo.carstore.exceptions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.edivaldo.carstore.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(Exception ex, WebRequest request) {
		return responseData(Arrays.asList(ex.getMessage()), request, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> resourceBadRequestException(Exception ex, WebRequest request) {
		return responseData(Arrays.asList(ex.getMessage()), request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		/*
		 * Loga o erro
		 */
		ex.printStackTrace();
		
		return responseData(Arrays.asList("Erro Inesperado, tente mais tarde!"), request,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<?> responseData(List<String> errors, WebRequest request, HttpStatus status) {
		String details = request.getDescription(false);
		Date timestamp = new Date();
		Response<Object> response = new Response<Object>(timestamp, errors, details);
		return new ResponseEntity<>(response, status);
	}
}
