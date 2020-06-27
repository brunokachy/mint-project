package com.mint.project.core.cardverification.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mint.project.core.cardverification.model.CardVerifyResult;
import com.mint.project.core.cardverification.service.CardIntegrationService;
import com.mint.project.core.cardverification.service.CardVerifyService;
import com.mint.project.core.shared.Card;
import com.mint.project.core.shared.CardPersistenceService;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-25
 */
@RequiredArgsConstructor
@Service
public class CardVerifyServiceImpl implements CardVerifyService {

	private final CardPersistenceService cardPersistenceService;

	private final CardIntegrationService cardIntegrationService;

	@Override
	public CardVerifyResult verify(final Integer cardNumber) {

		final Optional<Card> optionalCard = cardPersistenceService.findCardByCardNumber(cardNumber);

		if (optionalCard.isPresent()) {
			final Card existingCard = optionalCard.get();
			final Card card = Card.createExistingCard(
					existingCard.getId(),
					cardNumber,
					existingCard.getScheme(),
					existingCard.getType(),
					existingCard.getBank(),
					existingCard.getTotalRequest()
			);
			cardPersistenceService.updateCard(card);
			return buildResponse(card);
		}

		final Card card = cardIntegrationService.callCardApi(cardNumber);

		cardPersistenceService.saveCard(card);

		return buildResponse(card);
	}

	private CardVerifyResult buildResponse(final Card card) {
		return CardVerifyResult.of(card.isSuccess(), card.getScheme(), card.getType(), card.getBank());
	}
}
