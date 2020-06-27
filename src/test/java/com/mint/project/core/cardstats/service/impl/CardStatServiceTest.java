package com.mint.project.core.cardstats.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mint.project.core.cardstats.model.CardStatResult;
import com.mint.project.core.shared.CardPersistenceService;

/**
 * @author Bruno Okafor 2020-06-27
 */
class CardStatServiceTest {

	@InjectMocks
	CardStatServiceImpl cardStatService;

	@Mock
	private CardPersistenceService cardPersistenceService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getCardStats_whenCardDataRetrieved_returnCardStatResult() {

		final int limit = 5;
		final int start = 1;

		when(cardPersistenceService.getCards(anyInt(), anyInt())).thenReturn(Collections.EMPTY_LIST);

		final CardStatResult result = cardStatService.getCardStats(1, 5);

		assertNotNull(result);
		assertEquals(Collections.EMPTY_LIST.size(), result.getSize());
		assertEquals(limit, result.getLimit());
		assertEquals(start, result.getStart());
		assertTrue(result.isSuccess());

		verify(cardPersistenceService, times(1)).getCards(anyInt(), anyInt());
	}

}
