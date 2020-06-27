package com.mint.project.core.shared;

import java.util.List;
import java.util.Optional;

/**
 * @author Bruno Okafor 2020-06-25
 */
public interface CardPersistenceService {

	Optional<Card> findCardByCardNumber(Integer cardNumber);

	void saveCard(Card card);

	void updateCard(Card card);

	List<Card> getCards(int start, int limit);
}
