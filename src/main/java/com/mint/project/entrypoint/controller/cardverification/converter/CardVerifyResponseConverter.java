package com.mint.project.entrypoint.controller.cardverification.converter;

import com.mint.project.core.cardverification.model.CardVerifyResult;
import com.mint.project.entrypoint.controller.cardverification.dto.CardDetailPayload;
import com.mint.project.entrypoint.controller.cardverification.dto.CardVerifyResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardVerifyResponseConverter {

	public static CardVerifyResponse toCardVerifyResponse(final CardVerifyResult cardVerifyResult) {

		return CardVerifyResponse.of(
				cardVerifyResult.isSuccess(),
				CardDetailPayload.of(
						cardVerifyResult.getScheme(),
						cardVerifyResult.getType(),
						cardVerifyResult.getBank()
				)
		);
	}
}
