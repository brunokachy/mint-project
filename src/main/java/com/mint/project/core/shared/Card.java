package com.mint.project.core.shared;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Bruno Okafor 2020-06-25
 */
@Getter
@AllArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
public class Card {

	private boolean isSuccess;

	private String cardNumber;

	private String scheme;

	private String type;

	private String bank;

	private long totalRequest;

	public static Card createCard(
			final Boolean isSuccess,
			final String cardNumber,
			final String scheme,
			final String type,
			final String bank
	) {

		return new Card(
				isSuccess,
				cardNumber,
				scheme,
				type,
				bank,
				1
		);
	}

	public static Card createExistingCard(final Card card) {

		return new Card(
				Boolean.TRUE,
				card.getCardNumber(),
				card.getScheme(),
				card.getType(),
				card.getBank(),
				card.getTotalRequest() + 1
		);
	}

}
