package com.edivaldo.carstore.service.impl;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.carstore.dtos.CarDto;
import com.edivaldo.carstore.dtos.ModelDto;
import com.edivaldo.carstore.dtos.ProducerDto;
import com.edivaldo.carstore.entities.Car;
import com.edivaldo.carstore.entities.Model;
import com.edivaldo.carstore.entities.Producer;
import com.edivaldo.carstore.exceptions.ResourceBadRequestException;
import com.edivaldo.carstore.exceptions.ResourceNotFoundException;
import com.edivaldo.carstore.exceptions.RestException;
import com.edivaldo.carstore.repository.RepositoryCar;
import com.edivaldo.carstore.repository.RepositoryModel;
import com.edivaldo.carstore.repository.RepositoryPruducer;
import com.edivaldo.carstore.response.Response;
import com.edivaldo.carstore.service.ServiceCar;
import com.edivaldo.carstore.util.ProducerEnum;
import com.edivaldo.carstore.util.StatusCarEnum;

/**
 * Classe camada de implementação de serviços de carro
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */

@Service
public class ServiceCarImp implements ServiceCar {
	public static final Logger logger = LoggerFactory.getLogger(ServiceCarImp.class);

	private static final String PATTERN = "[0-9]{4}";

	@Autowired
	private RepositoryCar repositoryCar;

	@Autowired
	private RepositoryPruducer repositoryPruducer;

	@Autowired
	private RepositoryModel repositoryModel;

	@Override
	public List<Car> findAllCars() {
		return (List<Car>) repositoryCar.findAll();
	}

	@Override
	public Car saveOrUpdateCar(Car car) throws RestException {
		if (car != null) {
			if (car.getId() != null) {
				Car findCarById = findCarById(car.getId());
				car.setId(findCarById.getId());
			}
			car = repositoryCar.save(car);
		}
		return car;
	}

	@Override
	public Car findCarById(Long id) throws RestException {
		return repositoryCar.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado para o id :: " + id));
	}

	@Override
	public ResponseEntity<Response<CarDto>> findById(Long id) throws RestException {
		logger.info("find ID  {}", id);
		Response<CarDto> response = new Response<>();
		CarDto converterCarToDto = converterCarToDto(findCarById(id));
		response.setData(converterCarToDto);
		return new ResponseEntity<Response<CarDto>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws RestException {
		logger.info("deleta  car {}", id);
		repositoryCar.delete(findCarById(id));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public List<Car> findByQ(String q) throws ResourceBadRequestException {
		if (q != null && !q.isEmpty()) {
			return collectionsCar().filter(c -> {
				if (c.getName().contains(q) || c.getDescription().contains(q) || c.getModel().getBrand().contains(q)
						|| c.getModel().getProducer().getName().name().contains(q))
					return true;
				else
					return false;
			}).collect(Collectors.toList());

		}
		throw new ResourceBadRequestException("Erro com parametro fornecido!");
	}

	@Override
	public ResponseEntity<Response<List<CarDto>>> findByQ_(String q) throws ResourceBadRequestException {
		logger.info("findByQ_ de  {}", q.toString());
		Response<List<CarDto>> response = new Response<>();
		ArrayList<CarDto> arrayList = new ArrayList<CarDto>();
		List<Car> findByQ = findByQ(q);
		findByQ.forEach(c -> {
			CarDto converterCarToDto = converterCarToDto(c);
			arrayList.add(converterCarToDto);
		});

		response.setData(arrayList);
		return ResponseEntity.ok(response);
	}

	@Override
	public Map<StatusCarEnum, List<Car>> carsSold() {
		Map<StatusCarEnum, List<Car>> collect = collectionsCar().collect(groupingBy(Car::getStatus));
		return collect;
	}

	@Override
	public Map<Integer, List<Car>> byDecade() {
		return collectionsCar().collect(groupingBy(Car::getYear));
	}

	private Stream<Car> collectionsCar() {
		Iterable<Car> cars_ = repositoryCar.findAll();
		return StreamSupport.stream(cars_.spliterator(), false);
	}

	@Override
	public HashMap<ProducerEnum, Integer> byProducer() {
		Map<Model, List<Car>> collect = collectionsCar().collect(groupingBy(Car::getModel));
		HashMap<ProducerEnum, Integer> hashMap = new HashMap<ProducerEnum, Integer>();

		collect.entrySet().stream().forEach(e -> {
			hashMap.put(e.getKey().getProducer().getName(), e.getValue().size());
		});

		return hashMap;
	}

	@Override
	public List<Car> lastWeek() {

		Date dateNow = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dateNow);
		c.add(Calendar.DATE, -7);
		Date dateAnd = c.getTime();

		List<Car> collectCar = collectionsCar().filter(car -> {
			if (dateNow.compareTo(car.getCreated()) * car.getCreated().compareTo(dateAnd) >= 0)
				return true;
			else
				return false;
		}).collect(Collectors.toList());

		return collectCar;
	}

	@Override
	public ResponseEntity<Response<List<CarDto>>> findAll() throws RestException {
		logger.info("Lista de carros");
		Response<List<CarDto>> response = new Response<List<CarDto>>();
		List<CarDto> collectCar = collectionsCar().map(car -> converterCarToDto(car)).collect(Collectors.toList());
		response.setData(collectCar);
		return ResponseEntity.ok(response);
	}

	private CarDto converterCarToDto(Car car) {
		ProducerEnum name = car.getModel().getProducer().getName();
		ProducerDto producer = ProducerDto.builder().name(name.name()).build();
		ModelDto model = ModelDto.builder().brand(car.getModel().getBrand()).producer(producer).build();

		return CarDto.builder().created(car.getCreated()).description(car.getDescription()).model(model)
				.name(car.getName()).status(car.getStatus().name()).year(car.getYear()).updated(car.getUpdated())
				.build();

	}

	private Car converterDtoToCar(CarDto carDto) {
		try {
			String name = carDto.getName();
			String description = carDto.getDescription();

			String brand = carDto.getModel().getBrand();

			Producer producer = validProducer(carDto.getModel().getProducer().getName());

			if (producer != null && producer.getId() == null) {
				producer = repositoryPruducer.save(producer);
			}
			Model model = validModel(brand);
			if (model == null) {
				Model model_ = new Model(producer, brand);
				model = repositoryModel.save(model_);
			}

			Date created = carDto.getCreated();

			Date updated = carDto.getUpdated();
			StatusCarEnum status = StatusCarEnum.getStatusCarr(carDto.getStatus());
			Integer year = carDto.getYear();
			return new Car(name, description, model, created, updated, status, year);
		} catch (Exception e) {
			logger.info("Erro ao converter OBJ");
			e.printStackTrace();
		}
		return null;
	}

	private Producer validProducer(String nameProducer) {

		ProducerEnum producerEnum = ProducerEnum.getProducer(nameProducer);
		if (producerEnum == null)
			return null;

		Producer findByName = repositoryPruducer.findByName(producerEnum);
		if (findByName == null)
			return new Producer(producerEnum);

		return findByName;
	}

	private Model validModel(String brand) {
		Model findByBrand = repositoryModel.findByBrand(brand);
		return findByBrand;
	}

	@Override
	public ResponseEntity<Response<CarDto>> register(CarDto carDto, BindingResult result,
			UriComponentsBuilder ucBuilder) throws RestException {
		logger.info("cadastro  {}", carDto.toString());
		Response<CarDto> response = new Response<>();

		validaData(carDto, result);
		if (result.hasErrors()) {
			logger.info("Erro na validação de Dados {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Car converterDtoToCar = converterDtoToCar(carDto);
		repositoryCar.save(converterDtoToCar);
		response.setData(carDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/cars/{id}").buildAndExpand(converterDtoToCar.getId()).toUri());
		return new ResponseEntity<Response<CarDto>>(response, headers, HttpStatus.CREATED);
	}

	private void validaData(CarDto carDto, BindingResult result) {
		String nameProducer = carDto.getModel().getProducer().getName();
		Producer validProducer = validProducer(nameProducer);

		if (validProducer == null)
			result.addError(new ObjectError("producer - name", "[producer - name] Fabricante não existe."));

		boolean validNumber = validNumber(carDto.getYear().toString());
		if (!validNumber)
			result.addError(new ObjectError("car - Year", "[year] Ano de Fabricação Errado!"));

	}

	public boolean validNumber(String numero) {
		Pattern p = Pattern.compile(PATTERN);
		Matcher m1 = p.matcher(numero);
		if (!m1.matches()) {
			return false;
		}
		return true;
	}

	@Override
	public ResponseEntity<Response<CarDto>> updateCar(Long id, CarDto carDto, BindingResult result)
			throws RestException {
		logger.info("update de  {}", carDto.toString());
		Response<CarDto> response = new Response<>();

		Optional<Car> validaDataUpdate = validaDataUpdate(id, result);
		validaData(carDto, result);
		if (result.hasErrors()) {
			logger.info("Erro na validação de Dados {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Car converterDtoToCar = converterDtoToCar(carDto);
		converterDtoToCar.setId(validaDataUpdate.get().getId());
		Car saveCar = repositoryCar.save(converterDtoToCar);

		CarDto converterCarToDto = converterCarToDto(saveCar);
		response.setData(converterCarToDto);
		return ResponseEntity.ok(response);
	}

	private Optional<Car> validaDataUpdate(Long id, BindingResult result) {
		Optional<Car> car = repositoryCar.findById(id);
		if (!car.isPresent())
			result.addError(new ObjectError("car - id", "Carro não encontrado!"));
		return car;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<Response<CarDto>> updateCarPatch(Long id, CarDto carDto, BindingResult result) {
		logger.info("updateCarPatch de  {}", carDto.toString());
		Response<CarDto> response = new Response<>();
		try {

			Optional<Car> oldCar = validaDataUpdate(id, result);
			Car validaDataPatch = validaDataPatch(oldCar.get(), carDto, result);
			if (result.hasErrors()) {
				logger.info("Erro na validação de Dados {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Car saveCar = repositoryCar.save(validaDataPatch);

			CarDto converterCarToDto = converterCarToDto(saveCar);
			response.setData(converterCarToDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.info("update de  {}", carDto.toString());
			return (ResponseEntity<Response<CarDto>>) responseErroDefault();
		}
	}

	private Car validaDataPatch(Car newCar, CarDto carDto, BindingResult result) {

		if (carDto == null)
			result.addError(new ObjectError("car", "Informações insuficientes para atualização de dados."));

		boolean validNumber = validNumber(carDto.getYear().toString());
		if (!validNumber)
			result.addError(new ObjectError("car - Year", "[year] Ano de Fabricação Errado!"));

		if (carDto.getCreated() != null) {
			newCar.setCreated(carDto.getCreated());
		}
		if (carDto.getDescription() != null) {
			newCar.setDescription(carDto.getDescription());
		}
		if (carDto.getName() != null) {
			newCar.setName(carDto.getName());
		}
		if (carDto.getStatus() != null) {
			newCar.setStatus(StatusCarEnum.getStatusCarr(carDto.getStatus()));
		}
		if (carDto.getUpdated() != null) {
			newCar.setUpdated(carDto.getUpdated());
		}

		if (carDto.getYear() != null) {
			newCar.setYear(carDto.getYear());
		}

		return newCar;
	}

	private ResponseEntity<?> responseErroDefault() {
		Response<?> response = new Response<>();
		response.getErrors().add("Erro interno, tente mais tarde.");
		return ResponseEntity.badRequest().body(response);
	}
}
