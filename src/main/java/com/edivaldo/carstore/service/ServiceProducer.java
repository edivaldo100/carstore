package com.edivaldo.carstore.service;

import java.util.stream.Stream;

import com.edivaldo.carstore.entities.Producer;
/**
 * Classe camada de abstração de serviços de fabrica de carro
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
public interface ServiceProducer {
	
	/**
	 * metodo que bsca carro pelo fabricante
	 * 
	 * @param id
	 * @return ResponseEntity<Response<CarDto>>
	 */
	Stream<Producer> findAllCars();

}
