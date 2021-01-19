package com.edivaldo.carstore;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.Date;

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
import com.edivaldo.carstore.util.ProducerEnum;
import com.edivaldo.carstore.util.StatusCarEnum;

/**
 * Classe de testes de modelos de carros
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestModel {
	private static final Logger log = LoggerFactory.getLogger(TestModel.class);

	@Autowired
	private RepositoryPruducer repositoryPruducer;

	@Autowired
	private RepositoryModel repositoryModel;

	@Test
	public void testNewModel() throws FileNotFoundException {

		ProducerEnum nameProducer = ProducerEnum.FIAT;
		Producer producer = new Producer(nameProducer);

		Producer saveProducer = repositoryPruducer.save(producer);

		Model model = new Model(saveProducer, "Uno2021");

		String name = "Uno";
		Date created = new Date();
		Date updated = new Date();
		Integer year = 2021;
		Car car = new Car(name, "1.0 ", model, created, updated, StatusCarEnum.SOLD, year);
		Model save = repositoryModel.save(model);
		repositoryModel.save(new Model(producer, "Uno2023"));
		Iterable<Model> findAll = repositoryModel.findAll();
		assertNotNull(save);
	}

}
