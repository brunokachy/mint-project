package com.mint.project.core.exception;

import lombok.Getter;

/**
 * @author Bruno Okafor 2020-06-26
 */
@Getter
public class CardNotFoundException extends RuntimeException {

	public CardNotFoundException(final String message) {
		super(message);
	}

}
