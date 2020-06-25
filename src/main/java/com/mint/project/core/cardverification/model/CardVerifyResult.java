package com.mint.project.core.cardverification.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Bruno Okafor 2020-06-25
 */
@Getter
@AllArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
public class CardVerifyResult {

	private boolean success;

	private String scheme;

	private String type;

	private String bank;
}
