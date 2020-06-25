package com.mint.project.core.cardstats.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mint.project.core.cardstats.model.CardStatResult;
import com.mint.project.core.cardstats.service.CardStatService;
import com.mint.project.core.shared.Card;
import com.mint.project.core.shared.CardPersistenceService;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-25
 */
@RequiredArgsConstructor
@Service
public class CardStatServiceImpl implements CardStatService {

	private final CardPersistenceService cardPersistenceService;

	@Override
	public CardStatResult getCardStats(final int start, final int limit) {

		final Page<Card> cardPage = cardPersistenceService.getCards(start, limit);

		final long size = cardPage.getTotalElements();

		final Map<String, Long> payload = new HashMap<>();

		cardPage.get().forEach(card -> payload.put(card.getCardNumber(), card.getTotalRequest()));

		return CardStatResult.of(Boolean.TRUE, size, start, limit, payload);
	}
}
