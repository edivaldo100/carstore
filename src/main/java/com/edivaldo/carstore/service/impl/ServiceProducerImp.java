package com.edivaldo.carstore.service.impl;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldo.carstore.entities.Producer;
import com.edivaldo.carstore.repository.RepositoryPruducer;
import com.edivaldo.carstore.service.ServiceProducer;
/**
 * Classe camada de implementação de serviços de fabrica de carros
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@Service
public class ServiceProducerImp implements ServiceProducer {
	public static final Logger logger = LoggerFactory.getLogger(ServiceProducerImp.class);
	@Autowired
	private RepositoryPruducer repositoryProducer;

	@Override
	public Stream<Producer> findAllCars() {
		Iterable<Producer> findAll = repositoryProducer.findAll();
		return StreamSupport.stream(findAll.spliterator(), false);
	}

}
