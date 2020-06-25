package com.mint.project.core.cardstats.service;

import com.mint.project.core.cardstats.model.CardStatResult;

/**
 * @author Bruno Okafor 2020-06-25
 */
public interface CardStatService {

	CardStatResult getCardStats(int start, int limit);
}
