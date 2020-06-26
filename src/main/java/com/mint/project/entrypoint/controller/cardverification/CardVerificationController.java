package com.mint.project.entrypoint.controller.cardverification;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "card-scheme/verify")
	public ResponseEntity<CardVerifyResponse> verifyCard(
			@RequestParam(name = "cardNumber")
			@NotNull(message = "Please provide a card number")
			@Positive(message = "Card number must be a positive integer")
//			@Size(min = 6, max = 6, message = "Card number must be 6 digits")
			final int cardNumber
	) {

		final CardVerifyResult cardVerifyResult = cardVerifyService.verify(cardNumber);

		final CardVerifyResponse response = CardVerifyResponseConverter.toCardVerifyResponse(cardVerifyResult);

		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
}
