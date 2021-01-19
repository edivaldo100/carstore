package com.edivaldo.carstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edivaldo.carstore.util.ProducerEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe entidade de fabricante
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
@Table(name = "producer")
public class Producer implements Serializable {

	private static final long serialVersionUID = -1995008769486167671L;

	@Deprecated
	public Producer() {
	}

	public Producer(ProducerEnum name) {
		this.name = name;
	}

	public Producer(List<Model> models, ProducerEnum name) {
		this.models = models;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "producer_id")
	private List<Model> models;

	private ProducerEnum name;

}
