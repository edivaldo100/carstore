package com.edivaldo.carstore.util;

public enum ProducerEnum {

	/**
	 * Enum de representação de marcas/fabricantes
	 * 
	 * @author Edivaldo
	 * @version 1.0.0
	 * @since Release 01 da aplicação
	 */

	CHEVROLET, VOLKSWAGEM, FIAT, RENAULT, FORD;

	public static ProducerEnum getProducer(String name) {
		ProducerEnum[] values = ProducerEnum.values();
		for (ProducerEnum producerEnum : values) {
			if (name.equals(producerEnum.name()))
				return producerEnum;
		}
		return null;
	}
}
