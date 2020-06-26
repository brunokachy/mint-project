package com.mint.project.entrypoint.controller.cardverification.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

/**
 * @author Bruno Okafor 2020-06-25
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value(staticConstructor = "of")
public class CardDetailPayload {

	@NotBlank
	String scheme;

	@NotBlank
	String type;

	String bank;
}
