package com.edivaldo.carstore.util;

public class CustomErrorType {
	/**
	 * Classe de tipos de erros
	 * 
	 * @author Edivaldo
	 * @version 1.0.0
	 * @since Release 01 da aplicação
	 */
    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}