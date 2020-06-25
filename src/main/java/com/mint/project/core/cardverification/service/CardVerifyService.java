package com.mint.project.core.cardverification.service;

import com.mint.project.core.cardverification.model.CardVerifyResult;

/**
 * @author Bruno Okafor 2020-06-25
 */
public interface CardVerifyService {

	CardVerifyResult verify(Integer cardNumber);
}
