package com.mint.project.infrastructure.rest.dto;

import lombok.Data;

/**
 * @author Bruno Okafor 2020-06-26
 */
@Data
public class CardResolutionResponse {

	private Bank bank;

	private String scheme;

	private String type;

	private String brand;

	private boolean prepaid;

}