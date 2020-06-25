package com.mint.project.entrypoint.controller.cardstats.dto;

import java.util.Map;

import lombok.Value;

/**
 * @author Bruno Okafor 2020-06-25
 */
@Value(staticConstructor = "of")
public class CardStatResponse {

	boolean success;

	long size;

	int start;

	int limit;

	Map<String, Integer> payload;
}
