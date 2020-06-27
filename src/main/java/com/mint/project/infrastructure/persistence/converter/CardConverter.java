package com.mint.project.infrastructure.persistence.converter;

import com.mint.project.core.shared.Card;
import com.mint.project.infrastructure.persistence.entity.CardEntity;

/**
 * @author Bruno Okafor 2020-06-26
 */
public class CardConverter {

	public static CardEntity convertToExistingDatabaseEntity(final Card card) {

		return new CardEntity(card.getId(), card.getBank(), card.getScheme(), card.getTotalRequest(), card.getType());
	}

	public static CardEntity convertToNewDatabaseEntity(final Card card) {

		return new CardEntity(card.getBank(), card.getCardNumber(), card.getScheme(), card.getTotalRequest(), card.getType());

	}

	public static Card convertToDomain(final CardEntity cardEntity) {

		return Card.of(
				cardEntity.getId(),
				Boolean.TRUE,
				cardEntity.getCardNumber(),
				cardEntity.getScheme(),
				cardEntity.getType(),
				cardEntity.getBank(),
				cardEntity.getTotalRequest()
		);
	}
}
