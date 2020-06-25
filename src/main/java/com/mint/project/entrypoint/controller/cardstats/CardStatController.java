package com.mint.project.entrypoint.controller.cardstats;

import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mint.project.core.cardstats.model.CardStatResult;
import com.mint.project.core.cardstats.service.CardStatService;
import com.mint.project.entrypoint.controller.cardstats.converter.CardStatResponseConverter;
import com.mint.project.entrypoint.controller.cardstats.dto.CardStatResponse;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-25
 */
@RestController
@RequiredArgsConstructor
public class CardStatController {

	private final CardStatService cardStatService;

	@GetMapping(value = "card-scheme/stats")
	public ResponseEntity<CardStatResponse> getCardHitStats(
			@Positive @RequestParam(name = "start") final int start,
			@Positive @RequestParam(name = "limit") final int limit
	) {

		final CardStatResult cardStatResult = cardStatService.getCardStats(start, limit);

		final CardStatResponse response = CardStatResponseConverter.toCardStatResponse(cardStatResult);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
