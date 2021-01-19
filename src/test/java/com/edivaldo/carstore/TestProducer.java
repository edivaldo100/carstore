package com.edivaldo.carstore;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.carstore.entities.Car;
import com.edivaldo.carstore.entities.Model;
import com.edivaldo.carstore.entities.Producer;
import com.edivaldo.carstore.repository.RepositoryModel;
import com.edivaldo.carstore.repository.RepositoryPruducer;
import com.edivaldo.carstore.service.ServiceCar;
import com.edivaldo.carstore.service.ServiceProducer;
import com.edivaldo.carstore.util.ProducerEnum;
import com.edivaldo.carstore.util.StatusCarEnum;

/**
 * Classe de testes de fabricantes
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestProducer {
	private static final Logger log = LoggerFactory.getLogger(TestProducer.class);

	@Autowired
	private ServiceCar serviceCar;

	@Autowired
	private ServiceProducer serviceProducer;

	@Autowired
	private RepositoryPruducer repositoryPruducer;

	@Autowired
	private RepositoryModel repositoryModel;

	public Model model;
	public Producer producer;
	public Car car;

	@After
	public void after() {

	}

	@Before
	public void before() {
		producer = repositoryPruducer.save(new Producer(ProducerEnum.FIAT));
		model = repositoryModel.save(new Model(producer, "Uno2021"));
		startCollectionCars();
	}

	private void startCollectionCars() {
		String name = "Uno";
		Date created = new Date();
		Date updated = new Date();
		Integer year = 2021;
		car = serviceCar
				.saveOrUpdateCar(new Car(name, "4 portas 1.0", model, created, updated, StatusCarEnum.SOLD, year));

		serviceCar.saveOrUpdateCar(
				new Car("Uno classic", "4 portas 1.0", model, new Date(), new Date(), StatusCarEnum.SOLD, 1970));
		serviceCar.saveOrUpdateCar(new Car("Novo completo Palio 16v", "4 portas 1.0", model, new Date(), new Date(),
				StatusCarEnum.SOLD, 1980));
		serviceCar.saveOrUpdateCar(
				new Car("Strada turbo", "2 portas 1.0", model, new Date(), new Date(), StatusCarEnum.AVAILABLE, 1980));
		serviceCar.saveOrUpdateCar(
				new Car("SUV basico ", "2 portas 1.4", model, new Date(), new Date(), StatusCarEnum.SOLD, 1970));

		producer = repositoryPruducer.save(new Producer(ProducerEnum.CHEVROLET));
		model = repositoryModel.save(new Model(producer, "novos chevrolets 2021"));
		serviceCar.saveOrUpdateCar(
				new Car(" completo", "2 portas 1.4", model, new Date(), new Date(), StatusCarEnum.AVAILABLE, 1980));
		serviceCar.saveOrUpdateCar(
				new Car(" classic", "4 portas 1.0", model, new Date(), new Date(), StatusCarEnum.SOLD, 1930));
		serviceCar.saveOrUpdateCar(
				new Car("Novo completo 16v", "4 portas 1.0", model, new Date(), new Date(), StatusCarEnum.SOLD, 1980));
		serviceCar.saveOrUpdateCar(
				new Car(" turbo", "2 portas 1.0", model, new Date(), new Date(), StatusCarEnum.AVAILABLE, 1980));

		producer = repositoryPruducer.save(new Producer(ProducerEnum.RENAULT));
		model = repositoryModel.save(new Model(producer, "novos clios 2021"));

		serviceCar.saveOrUpdateCar(
				new Car(" basico ", "2 portas 1.4", model, new Date(), new Date(), StatusCarEnum.SOLD, 1930));
		serviceCar.saveOrUpdateCar(
				new Car(" completo", "2 portas 1.4", model, new Date(), new Date(), StatusCarEnum.AVAILABLE, 1980));
	}

	@Test
	public void testNew() throws FileNotFoundException {

		ProducerEnum nameProducer = ProducerEnum.FIAT;
		Producer producer = new Producer(nameProducer);

		Producer save = repositoryPruducer.save(producer);
		repositoryPruducer.save(new Producer(ProducerEnum.FORD));
		repositoryPruducer.save(new Producer(ProducerEnum.CHEVROLET));

		Iterable<Producer> findAll = repositoryPruducer.findAll();
		assertNotNull(save);
	}

}
