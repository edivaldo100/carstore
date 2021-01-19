package com.edivaldo.carstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edivaldo.carstore.entities.Producer;
import com.edivaldo.carstore.util.ProducerEnum;
/**
 * Classe repository de uma fabrica
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@Repository
public interface RepositoryPruducer extends CrudRepository<Producer, Long> {
	Producer findByName(ProducerEnum name);
}