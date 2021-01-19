package com.edivaldo.carstore;

import java.io.FileNotFoundException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edivaldo.carstore.entities.Car;
import com.edivaldo.carstore.entities.Model;
import com.edivaldo.carstore.entities.Producer;
import com.edivaldo.carstore.repository.RepositoryModel;
import com.edivaldo.carstore.repository.RepositoryPruducer;
import com.edivaldo.carstore.service.ServiceCar;
import com.edivaldo.carstore.util.ProducerEnum;
import com.edivaldo.carstore.util.StatusCarEnum;

/**
 * Classe de execução
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@Component
public class RunApp {

	@Autowired
	private ServiceCar serviceCar;
	public Model model;
	public Producer producer;
	public Car car;

	@Autowired
	private RepositoryPruducer repositoryPruducer;
	@Autowired
	private RepositoryModel repositoryModel;

	/**
	 * Método para executor de criação de carros para popular a base
	 * 
	 * @return void
	 * @throws FileNotFoundException
	 */
	public void appStart() throws FileNotFoundException {

		producer = repositoryPruducer.save(new Producer(ProducerEnum.FORD));
		model = repositoryModel.save(new Model(producer, "NOVO FORD 2021"));

		serviceCar.saveOrUpdateCar(
				new Car(" classic", "4 portas 1.0", model, getDateMoth(), getDateMoth(), StatusCarEnum.SOLD, 1970));
		serviceCar.saveOrUpdateCar(new Car("Novo completo  16v", "4 portas 1.0", model, getDateMoth(), getDateMoth(),
				StatusCarEnum.SOLD, 1980));
		serviceCar.saveOrUpdateCar(
				new Car(" turbo", "2 portas 1.0", model, getDateMoth(), getDateMoth(), StatusCarEnum.AVAILABLE, 1980));
		serviceCar.saveOrUpdateCar(
				new Car("SUV basico ", "2 portas 1.4", model, getDateMoth(), getDateMoth(), StatusCarEnum.SOLD, 1970));

		producer = repositoryPruducer.save(new Producer(ProducerEnum.RENAULT));
		model = repositoryModel.save(new Model(producer, "NOVO CLIO 2021"));
		serviceCar.saveOrUpdateCar(
				new Car(" turbo", "2 portas 1.0", model, getDateMoth(), getDateMoth(), StatusCarEnum.AVAILABLE, 1980));
		serviceCar.saveOrUpdateCar(
				new Car("SUV basico ", "2 portas 1.4", model, getDateMoth(), getDateMoth(), StatusCarEnum.SOLD, 1970));

	}

	public static int generate(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	private Date getDateMoth() {
		Date date = new Date();
		date.setDate(generate(1, 7));
		return date;

	}
}
