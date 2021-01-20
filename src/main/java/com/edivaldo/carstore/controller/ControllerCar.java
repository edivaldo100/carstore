package com.edivaldo.carstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.carstore.dtos.CarDto;
import com.edivaldo.carstore.exceptions.RestException;
import com.edivaldo.carstore.response.Response;
import com.edivaldo.carstore.service.ServiceCar;

/**
 * Classe de controller
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class ControllerCar {
	@Autowired
	private ServiceCar serviceCar;

	/**
	 * metodo que lista todos os carros
	 * 
	 * @return ResponseEntity<Response<List<CarDto>>>
	 * @throws RestException
	 */
	@GetMapping
	public ResponseEntity<Response<List<CarDto>>> listAll() throws RestException {
		return serviceCar.findAll();
	}

	/**
	 * metodo que lista todos os carros pelo parametro passado
	 * 
	 * @param q
	 * @return ResponseEntity<Response<List<CarDto>>>
	 * @throws RestException
	 */
	@GetMapping(value = "/find")
	public ResponseEntity<Response<List<CarDto>>> listAllByQ(@RequestParam(required = true) String q)
			throws RestException {
		return serviceCar.findByQ_(q);
	}

	/**
	 * metodo que cria um carro
	 * 
	 * @param carDto
	 * @param result
	 * @param ucBuilder
	 * @return ResponseEntity<?>
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CarDto carDto, BindingResult result, UriComponentsBuilder ucBuilder)
			throws RestException {
		return serviceCar.register(carDto, result, ucBuilder);
	}

	/**
	 * metodo que busca um carro pelo ID
	 * 
	 * @param id
	 * @return ResponseEntity<Response<CarDto>>
	 * @throws RestException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CarDto>> findById(@PathVariable("id") Long id) throws RestException {
		return serviceCar.findById(id);
	}

	/**
	 * metodo que atualiza um carro pelo ID e parametros
	 * 
	 * @param id
	 * @param carDto
	 * @param result
	 * @return ResponseEntity<Response<CarDto>>
	 * @throws RestException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<CarDto>> updateCar(@PathVariable("id") Long id, @RequestBody CarDto carDto,
			BindingResult result) throws RestException {
		return serviceCar.updateCar(id, carDto, result);
	}

	/**
	 * metodo que atualiza um carro pelo ID e parametros especificaos
	 * 
	 * @param id
	 * @param carDto
	 * @param result
	 * @return ResponseEntity<Response<CarDto>>
	 */
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Response<CarDto>> updateCarPatch(@PathVariable("id") Long id, @RequestBody CarDto carDto,
			BindingResult result) {
		return serviceCar.updateCarPatch(id, carDto, result);
	}

	/**
	 * Method for delete car
	 * 
	 * @param id
	 * @return ResponseEntity<?>
	 * @throws RestException
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delet(@PathVariable("id") Long id) throws RestException {
		return serviceCar.delete(id);
	}
}
