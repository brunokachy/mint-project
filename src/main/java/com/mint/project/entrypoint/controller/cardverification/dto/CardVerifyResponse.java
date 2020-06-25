package com.mint.project.entrypoint.controller.cardverification.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Value;

/**
 * @author Bruno Okafor 2020-06-25
 */
@Value(staticConstructor = "of")
public class CardVerifyResponse {

	@NotNull
	boolean success;

	@Valid
	CardDetailPayload payload;
}
