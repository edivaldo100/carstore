package com.edivaldo.carstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edivaldo.carstore.entities.Car;

/**
 * Classe repository de carro
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@Repository
public interface RepositoryCar extends CrudRepository<Car, Long> {
}