package com.mint.project.core.shared;

import java.util.Optional;

import org.springframework.data.domain.Page;

/**
 * @author Bruno Okafor 2020-06-25
 */
public interface CardPersistenceService {

	Optional<Card> findCardByCardNumber(int cardNumber);

	void saveCard(Card card);

	Page<Card>  getCards(int start, int limit);
}
