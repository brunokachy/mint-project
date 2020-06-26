package com.mint.project.infrastructure.util;

import lombok.Value;

/**
 * @author Bruno Okafor 2020-06-26
 */
@Value(staticConstructor = "of")
class GatewayError {

	String errorMessage;

	String status;

	String time;
}

