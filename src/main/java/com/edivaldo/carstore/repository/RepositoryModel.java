package com.edivaldo.carstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edivaldo.carstore.entities.Model;
/**
 * Classe repository de modelos de carros de uma fabrica
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@Repository
public interface RepositoryModel extends CrudRepository<Model, Long> {
	
	Model findByBrand(String brand);
}