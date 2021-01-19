package com.edivaldo.carstore.dtos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

/**
 * Classe de DTO de @Car
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto implements Serializable {

	private static final long serialVersionUID = -1995008769486167671L;

	private Long id;

	private String name;

	private String description;

	private ModelDto model;

	private Date created;

	private Date updated;

	private String status;

	private Integer year;
}
