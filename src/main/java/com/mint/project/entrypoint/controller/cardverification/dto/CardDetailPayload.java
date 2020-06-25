package com.mint.project.entrypoint.controller.cardverification.dto;

import javax.validation.constraints.NotBlank;

import lombok.Value;

/**
 * @author Bruno Okafor 2020-06-25
 */
@Value(staticConstructor = "of")
public class CardDetailPayload {

	@NotBlank
	String scheme;

	@NotBlank
	String type;

	@NotBlank
	String bank;
}
