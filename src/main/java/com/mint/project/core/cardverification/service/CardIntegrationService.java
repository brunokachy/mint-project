package com.mint.project.core.cardverification.service;

import com.mint.project.core.shared.Card;

/**
 * @author Bruno Okafor 2020-06-25
 */
public interface CardIntegrationService {

	Card callCardApi(int cardNumber);
}
