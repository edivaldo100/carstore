package com.edivaldo.carstore.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

/**
 * Classe de DTO de @Producer
 * 
 * @author Edivaldo
 * @version 1.0.0
 * @since Release 01 da aplicação
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProducerDto implements Serializable {

	private static final long serialVersionUID = -1995008769486167671L;

	private Long id;

	private String name;

}
