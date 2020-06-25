package com.mint.project.entrypoint.controller.cardstats.converter;

import com.mint.project.core.cardstats.model.CardStatResult;
import com.mint.project.entrypoint.controller.cardstats.dto.CardStatResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardStatResponseConverter {

	public static CardStatResponse toCardStatResponse(final CardStatResult cardStatResult) {

		return CardStatResponse.of(
				cardStatResult.isSuccess(),
				cardStatResult.getSize(),
				cardStatResult.getStart(),
				cardStatResult.getLimit(),
				cardStatResult.getPayload()
		);
	}
}
