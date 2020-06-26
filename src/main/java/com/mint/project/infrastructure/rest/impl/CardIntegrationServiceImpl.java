package com.mint.project.infrastructure.rest.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mint.project.core.cardverification.service.CardIntegrationService;
import com.mint.project.core.exception.CardNotFoundException;
import com.mint.project.core.shared.Card;
import com.mint.project.infrastructure.messaging.CardMessage;
import com.mint.project.infrastructure.messaging.Producer;
import com.mint.project.infrastructure.rest.RestService;
import com.mint.project.infrastructure.rest.dto.Bank;
import com.mint.project.infrastructure.rest.dto.CardResolutionResponse;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-26
 */
@Service
@RequiredArgsConstructor
public class CardIntegrationServiceImpl implements CardIntegrationService {

	private final RestService restService;

	private final Producer producer;

	@Value("${card-resolution-service-url}")
	private String uriPath;

	@Override
	public Card callCardApi(final int cardNumber) {

		final String URI_PATH = String.format("%s/%d", uriPath, cardNumber);

		ResponseEntity<CardResolutionResponse> responseEntity = restService.getForEntity(CardResolutionResponse.class, URI_PATH);

		if (responseEntity == null || responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null) {
			throw new CardNotFoundException("Unable to resolve card details at the moment");
		}

		CardResolutionResponse cardResolutionResponse = responseEntity.getBody();
		String type = cardResolutionResponse.getType();
		String scheme = cardResolutionResponse.getScheme();
		String bankName = "";

		if (cardResolutionResponse.getBank() != null) {
			Bank bank = cardResolutionResponse.getBank();
			bankName = bank.getName();
		}

		CardMessage cardMessage = new CardMessage();
		cardMessage.setBank(bankName);
		cardMessage.setScheme(scheme);
		cardMessage.setType(type);
		producer.sendMessage(cardMessage);

		return Card.createCard(
				Boolean.TRUE,
				String.valueOf(cardNumber),
				scheme,
				type,
				bankName
		);
	}

}