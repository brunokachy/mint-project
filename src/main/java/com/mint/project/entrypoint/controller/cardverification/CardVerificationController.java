package com.mint.project.entrypoint.controller.cardverification;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mint.project.core.cardverification.model.CardVerifyResult;
import com.mint.project.core.cardverification.service.CardVerifyService;
import com.mint.project.entrypoint.controller.cardverification.converter.CardVerifyResponseConverter;
import com.mint.project.entrypoint.controller.cardverification.dto.CardVerifyResponse;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-25
 */
@RestController
@RequiredArgsConstructor
public class CardVerificationController {

	private final CardVerifyService cardVerifyService;

	@GetMapping(value = "card-scheme/verify/{cardNumber}")
	public ResponseEntity<CardVerifyResponse> verifyCard(@PathVariable("cardNumber") @NotBlank final String cardNumber) {

		final CardVerifyResult cardVerifyResult = cardVerifyService.verify(cardNumber);

		final CardVerifyResponse response = CardVerifyResponseConverter.toCardVerifyResponse(cardVerifyResult);

		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
}
