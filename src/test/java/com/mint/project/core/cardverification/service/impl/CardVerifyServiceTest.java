package com.mint.project.core.cardverification.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mint.project.core.cardverification.model.CardVerifyResult;
import com.mint.project.core.cardverification.service.CardIntegrationService;
import com.mint.project.core.shared.Card;
import com.mint.project.core.shared.CardPersistenceService;

/**
 * @author Bruno Okafor 2020-06-27
 */
class CardVerifyServiceTest {

	@InjectMocks
	private CardVerifyServiceImpl cardVerifyService;

	@Mock
	private CardPersistenceService cardPersistenceService;

	@Mock
	private CardIntegrationService cardIntegrationService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	void verify_whenCardDataInDB_updateCardDataAndReturnCardVerifyResult() {
//		final Card card = createCard();
//
//
//		when(cardPersistenceService.findCardByCardNumber(anyInt())).thenReturn(Optional.of(card));
//
//		final CardVerifyResult result = cardVerifyService.verify(0305304);
//
//		assertNotNull(result);
//		assertEquals(card.getBank(), result.getBank());
//		assertEquals(card.getScheme(), result.getScheme());
//		assertEquals(card.getTotalRequest(), 1);
//		assertEquals(card.getType(), result.getType());
//		assertTrue(result.isSuccess());
//
//		verify(cardIntegrationService, times(0)).callCardApi(anyInt());
//		verify(cardPersistenceService, times(1)).findCardByCardNumber(anyInt());
//		verify(cardPersistenceService, times(0)).saveCard(card);
//		verify(cardPersistenceService, times(1)).updateCard(card);
//	}

	@Test
	void verify_whenCardDataNotInDB_callExternalGatewayAndReturnCardVerifyResult() {

		final Card card = createCard();

		when(cardPersistenceService.findCardByCardNumber(anyInt())).thenReturn(Optional.empty());
		when(cardIntegrationService.callCardApi(anyInt())).thenReturn(card);

		final CardVerifyResult result = cardVerifyService.verify(0305304);

		assertNotNull(result);
		assertEquals(card.getBank(), result.getBank());
		assertEquals(card.getScheme(), result.getScheme());
		assertEquals(card.getTotalRequest(), 1);
		assertEquals(card.getType(), result.getType());
		assertTrue(result.isSuccess());

		verify(cardIntegrationService, times(1)).callCardApi(anyInt());
		verify(cardPersistenceService, times(1)).findCardByCardNumber(anyInt());
		verify(cardPersistenceService, times(1)).saveCard(card);
		verify(cardPersistenceService, times(0)).updateCard(card);
	}

	private Card createCard() {
		return Card.createCard(
				Boolean.TRUE,
				0305304,
				"scheme",
				"type",
				"bank"
		);
	}
}
