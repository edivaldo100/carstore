package com.edivaldo.carstore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.carstore.dtos.CarDto;
import com.edivaldo.carstore.entities.Car;
import com.edivaldo.carstore.exceptions.RestException;
import com.edivaldo.carstore.response.Response;
import com.edivaldo.carstore.util.ProducerEnum;
import com.edivaldo.carstore.util.StatusCarEnum;

/**
 * Classe camada de abstração de serviços de carro
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */

public interface ServiceCar {

	/**
	 * metodo que busca um carro pelo id informado
	 * 
	 * @param id
	 * @return Optional<Car>
	 */
	Optional<Car> findCar(Long id);

	/**
	 * metodo que busca todos os carros
	 * 
	 * @return List<Car>
	 */
	List<Car> findAllCars();

	/**
	 * metodo que busca todos os carros
	 * 
	 * @return ResponseEntity<Response<List<CarDto>>>
	 */
	ResponseEntity<Response<List<CarDto>>> findAll();

	/**
	 * metodo que busca os carros por parametro informado
	 * 
	 * @param q
	 * @return ResponseEntity<Response<List<CarDto>>>
	 * @throws RestException
	 */
	List<Car> findByQ(String q) throws RestException;

	/**
	 * metodo salva um carros
	 * 
	 * @param car
	 * @return Car
	 */
	Car saveOrUpdateCar(Car car);

	/**
	 * metodo que remove um carros
	 * 
	 * @param id
	 * @return void
	 */
	void removeCar(Long id);

	/**
	 * metodo que lista os carros vendido dos carros disponivel
	 * 
	 * @return Map<StatusCarEnum, List<Car>>
	 */
	Map<StatusCarEnum, List<Car>> carsSold();

	/**
	 * metodo que lista os carros separados por ano de fabricação/ decada
	 * 
	 * @return Map<Integer, List<Car>>
	 */
	Map<Integer, List<Car>> byDecade();

	/**
	 * metodo que lista os carros separados fabricante
	 * 
	 * @return HashMap<ProducerEnum, Integer>
	 */
	HashMap<ProducerEnum, Integer> byProducer();

	/**
	 * metodo que lista os carros venddos na ultima semana
	 * 
	 * @return List<Car>
	 */
	List<Car> lastWeek();

	/**
	 * metodo que salva um carro na base
	 * 
	 * @param carDto
	 * @param result
	 * @param ucBuilder
	 * @return ResponseEntity<Response<CarDto>>
	 */
	ResponseEntity<Response<CarDto>> register(CarDto carDto, BindingResult result, UriComponentsBuilder ucBuilder);

	/**
	 * metodo que bsca carro pelo id
	 * 
	 * @param id
	 * @return ResponseEntity<Response<CarDto>>
	 */
	ResponseEntity<Response<CarDto>> findById(Long id);

	/**
	 * metodo que atualiza um carro na base
	 * 
	 * @param carDto
	 * @param result
	 * @param id
	 * @return ResponseEntity<Response<CarDto>>
	 */
	ResponseEntity<Response<CarDto>> updateCar(Long id, CarDto carDto, BindingResult result);

	/**
	 * metodo que atualiza um carro na base
	 * 
	 * @param carDto
	 * @param result
	 * @param id
	 * @return ResponseEntity<Response<CarDto>>
	 */
	ResponseEntity<Response<CarDto>> updateCarPatch(Long id, CarDto carDto, BindingResult result);

	/**
	 * metodo que busca carros por parametro informado
	 * 
	 * @param q
	 * @return ResponseEntity<Response<List<CarDto>>>
	 */
	ResponseEntity<Response<List<CarDto>>> findByQ_(String q);

	/**
	 * metodo que apaga um carro
	 * 
	 * @param id
	 * @return ResponseEntity<?>
	 */
	ResponseEntity<?> delete(Long id);

}
