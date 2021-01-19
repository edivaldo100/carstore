package com.edivaldo.carstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe entidade de modelo
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Model implements Serializable {

	private static final long serialVersionUID = -1995008769486167671L;

	@Deprecated
	public Model() {
	}

	public Model(Producer producer, String brand) {
		this.producer = producer;
		this.brand = brand;
	}

	public Model(List<Car> cars, Producer producer, String brand) {
		this.cars = cars;
		this.producer = producer;
		this.brand = brand;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "model_id")
	private List<Car> cars;

	@ManyToOne
	@JoinColumn(name = "producer_id")
	private Producer producer;

	private String brand;
}
