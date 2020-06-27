package com.mint.project.core.shared;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Bruno Okafor 2020-06-25
 */
@Getter
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class Card {

	private long id;

	private boolean isSuccess;

	private Integer cardNumber;

	private String scheme;

	private String type;

	private String bank;

	private long totalRequest;

	public static Card createCard(
			final Boolean isSuccess,
			final Integer cardNumber,
			final String scheme,
			final String type,
			final String bank
	) {

		return new Card(
				0L,
				isSuccess,
				cardNumber,
				scheme,
				type,
				bank,
				1
		);
	}

	public static Card createExistingCard(
			final long id,
			final Integer cardNumber,
			final String scheme,
			final String type,
			final String bank,
			final long totalRequest
	) {

		return new Card(
				id,
				Boolean.TRUE,
				cardNumber,
				scheme,
				type,
				bank,
				totalRequest + 1
		);
	}

}
