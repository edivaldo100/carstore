package com.edivaldo.carstore.util;

public enum StatusCarEnum {
	
	/**
	 * Enum de representação de Status do veiculo vendido/disponivel
	 * 
	 * @author Edivaldo
	 * @version 1.0.0
	 * @since Release 01 da aplicação
	 */
	
	SOLD, AVAILABLE;
	
	public static StatusCarEnum getStatusCarr(String name) {
		StatusCarEnum[] values = StatusCarEnum.values();
		for (StatusCarEnum statusCarEnum : values) {
			if (name.equals(statusCarEnum.name()))
				return statusCarEnum;
		}
		return null;
	}
}
