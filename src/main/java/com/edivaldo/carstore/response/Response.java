package com.edivaldo.carstore.response;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Classe generica de respostas
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
public class Response<T> {

	private T data;
	private List<String> errors;

	private Date timestamp;

	private String details;

	
	
	public Response() {
	}
	public Response(Date timestamp, List<String> errors, String details) {
		super();
		this.errors = errors;
		this.timestamp = timestamp;
		this.details = details;
	}

	
	
	public Date getTimestamp() {
		return timestamp;
	}



	public String getDetails() {
		return details;
	}
	


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
