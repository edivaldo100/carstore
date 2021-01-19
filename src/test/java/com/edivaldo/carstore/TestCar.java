package com.edivaldo.carstore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.edivaldo.carstore.repository.RepositoryCar;
import com.edivaldo.carstore.repository.RepositoryModel;
import com.edivaldo.carstore.repository.RepositoryPruducer;
import com.edivaldo.carstore.service.ServiceCar;
import com.edivaldo.carstore.util.ProducerEnum;
import com.edivaldo.carstore.util.StatusCarEnum;

/**
 * Classe de testes de carros
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCar {
	private static final Logger log = LoggerFactory.getLogger(TestCar.class);

	@Autowired
	private ServiceCar serviceCar;

	@Autowired
	private RepositoryCar repositoryCar;
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
		serviceCar.saveOrUpdateCar(new Car("Palio completo", "2 portas 1.4", model, new Date(), new Date(),
				StatusCarEnum.AVAILABLE, 1980));
		serviceCar.saveOrUpdateCar(
				new Car("Uno classic", "4 portas 1.0", model, new Date(), new Date(), StatusCarEnum.SOLD, 1930));
		serviceCar.saveOrUpdateCar(new Car("Novo completo Palio 16v", "4 portas 1.0", model, new Date(), new Date(),
				StatusCarEnum.SOLD, 1980));
		serviceCar.saveOrUpdateCar(
				new Car("Strada turbo", "2 portas 1.0", model, new Date(), new Date(), StatusCarEnum.AVAILABLE, 1980));
		serviceCar.saveOrUpdateCar(
				new Car("SUV basico ", "2 portas 1.4", model, new Date(), new Date(), StatusCarEnum.SOLD, 1930));
		serviceCar.saveOrUpdateCar(new Car("Palio completo", "2 portas 1.4", model, new Date(), new Date(),
				StatusCarEnum.AVAILABLE, 1980));
	}

	@Test
	public void testNewCar() {
		try {

			String name = "Uno";
			Date created = new Date();
			Date updated = new Date();
			Integer year = 2021;

			Car save = repositoryCar
					.save(new Car("Palio", "4 portas 1.0", model, created, updated, StatusCarEnum.SOLD, year));

			Iterable<Car> findAll = repositoryCar.findAll();
			assertNotNull(save);
		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllCars() {
		try {

			List<Car> findAllCars = serviceCar.findAllCars();
			assertNotNull(findAllCars);
			assertTrue(!findAllCars.isEmpty());

		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testDeletCars() {
		try {

			serviceCar.removeCar(car.getId());
			List<Car> findAllCarsNew = serviceCar.findAllCars();
			assertTrue(findAllCarsNew.isEmpty());
		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateCars() {
		try {
			log.info("Car: " + car);

			List<Car> findAllCars = serviceCar.findAllCars();
			car.setName("NOVO PALIDOO");
			Car saveOrUpdateCar = serviceCar.saveOrUpdateCar(car);
			List<Car> findAllCarsNew = serviceCar.findAllCars();
			assertNotNull(saveOrUpdateCar);
		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testFindByQCars() {
		try {

			List<Car> findAllCars = serviceCar.findAllCars();
			System.out.println("QUANTIADE: " + findAllCars.size());

			List<Car> findByQ = serviceCar.findByQ("completo");
			System.out.println("QUANTIDADE PESQUISA: " + findByQ.size());
			assertTrue(findByQ.size() == 2);

			findByQ = serviceCar.findByQ("turbo");
			System.out.println("QUANTIDADE PESQUISA turbo : " + findByQ.size());
			assertTrue(findByQ.size() == 1);

			findByQ = serviceCar.findByQ("2 portas");
			System.out.println("QUANTIDADE PESQUISA 2 portas : " + findByQ.size());
			assertTrue(findByQ.size() == 3);

		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testStatusCar() {
		try {

			Map<StatusCarEnum, List<Car>> carsSold = serviceCar.carsSold();

			List<Car> listAVAILABLE = carsSold.get(StatusCarEnum.AVAILABLE);
			List<Car> listSOLD = carsSold.get(StatusCarEnum.SOLD);

			System.out.println("TOTAL DE CARROS : " + serviceCar.findAllCars().size());

			System.out.println("TOTAL DE CARROS VENDIDOS : " + listSOLD.size());
			System.out.println("TOTAL DE CARROS DISPONIVEL : " + listAVAILABLE.size());
			assertTrue(listAVAILABLE.size() > 1);
			assertTrue(listSOLD.size() > 1);

		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testByDecadeCar() {
		try {

			Map<Integer, List<Car>> byDecade = serviceCar.byDecade();

			byDecade.entrySet().stream().forEach(e -> {
				System.out.println("CARROS DECADA : " + e.getKey());
				e.getValue()
						.forEach(car -> System.out.println("---> " + car.getName() + " " + car.getModel().getBrand()));
			});

			assertNotNull(byDecade);

		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testByProducerCar() {
		try {

			producer = repositoryPruducer.save(new Producer(ProducerEnum.FORD));
			model = repositoryModel.save(new Model(producer, "NOVO FORD 2021"));

			serviceCar.saveOrUpdateCar(
					new Car(" classic", "4 portas 1.0", model, new Date(), new Date(), StatusCarEnum.SOLD, 1970));
			serviceCar.saveOrUpdateCar(new Car("Novo completo  16v", "4 portas 1.0", model, new Date(), new Date(),
					StatusCarEnum.SOLD, 1980));
			serviceCar.saveOrUpdateCar(
					new Car(" turbo", "2 portas 1.0", model, new Date(), new Date(), StatusCarEnum.AVAILABLE, 1980));
			serviceCar.saveOrUpdateCar(
					new Car("SUV basico ", "2 portas 1.4", model, new Date(), new Date(), StatusCarEnum.SOLD, 1970));

			producer = repositoryPruducer.save(new Producer(ProducerEnum.RENAULT));
			model = repositoryModel.save(new Model(producer, "NOVO CLIO 2021"));
			serviceCar.saveOrUpdateCar(
					new Car(" turbo", "2 portas 1.0", model, new Date(), new Date(), StatusCarEnum.AVAILABLE, 1980));
			serviceCar.saveOrUpdateCar(
					new Car("SUV basico ", "2 portas 1.4", model, new Date(), new Date(), StatusCarEnum.SOLD, 1970));

			HashMap<ProducerEnum, Integer> byProducer = serviceCar.byProducer();

			byProducer.entrySet().stream().forEach(e -> {
				System.out.println("MARCA : " + e.getKey() + " Total: " + e.getValue());
			});

			assertNotNull(byProducer);

		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
	}

	@Test
	public void testLastWeek() {
		try {

			producer = repositoryPruducer.save(new Producer(ProducerEnum.FORD));
			model = repositoryModel.save(new Model(producer, "NOVO FORD 2021"));

			serviceCar.saveOrUpdateCar(
					new Car(" classic", "4 portas 1.0", model, getDateMoth(), getDateMoth(), StatusCarEnum.SOLD, 1970));
			serviceCar.saveOrUpdateCar(new Car("Novo completo  16v", "4 portas 1.0", model, getDateMoth(),
					getDateMoth(), StatusCarEnum.SOLD, 1980));
			serviceCar.saveOrUpdateCar(new Car(" turbo", "2 portas 1.0", model, getDateMoth(), getDateMoth(),
					StatusCarEnum.AVAILABLE, 1980));
			serviceCar.saveOrUpdateCar(new Car("SUV basico ", "2 portas 1.4", model, getDateMoth(), getDateMoth(),
					StatusCarEnum.SOLD, 1970));

			producer = repositoryPruducer.save(new Producer(ProducerEnum.RENAULT));
			model = repositoryModel.save(new Model(producer, "NOVO CLIO 2021"));
			serviceCar.saveOrUpdateCar(new Car(" turbo", "2 portas 1.0", model, getDateMoth(), getDateMoth(),
					StatusCarEnum.AVAILABLE, 1980));
			serviceCar.saveOrUpdateCar(new Car("SUV basico ", "2 portas 1.4", model, getDateMoth(), getDateMoth(),
					StatusCarEnum.SOLD, 1970));

			List<Car> lastWeek = serviceCar.lastWeek();

			System.out.println("OTTAL ULTIMA SEMANA : " + lastWeek.size());

			assertNotNull(lastWeek);

		} catch (Exception e) {
			log.error("ERROOO");
			e.printStackTrace();
		}
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
