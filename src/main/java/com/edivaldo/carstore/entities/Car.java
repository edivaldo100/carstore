package com.edivaldo.carstore.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.edivaldo.carstore.util.StatusCarEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Classe entidade de carro
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
public class Car implements Serializable {

	private static final long serialVersionUID = -1995008769486167671L;

	@Deprecated
	public Car() {
	}

	public Car(String name, String description, Model model, Date created, Date updated, StatusCarEnum status,
			Integer year) {
		this.name = name;
		this.description = description;
		this.model = model;
		this.created = created;
		this.updated = updated;
		this.status = status;
		this.year = year;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	private StatusCarEnum status;

	private Integer year;
}
