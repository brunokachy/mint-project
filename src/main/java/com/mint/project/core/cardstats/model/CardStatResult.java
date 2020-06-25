package com.mint.project.core.cardstats.model;

import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Bruno Okafor 2020-06-25
 */
@Getter
@AllArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
public class CardStatResult {

	private boolean success;

	private long size;

	private int start;

	private int limit;

	private Map<String, Integer> payload;
}
