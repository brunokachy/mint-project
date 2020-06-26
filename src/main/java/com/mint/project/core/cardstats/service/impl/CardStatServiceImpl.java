package com.mint.project.core.cardstats.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

		final List<Card> cardPage = cardPersistenceService.getCards(start, limit);

		final long size = cardPage.size();

		final Map<String, Long> payload = cardPage.stream().collect(Collectors.toMap(Card::getCardNumber, Card::getTotalRequest, (a, b) -> b));

		return CardStatResult.of(Boolean.TRUE, size, start, limit, payload);
	}
}
