package com.mint.project.infrastructure.messaging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bruno Okafor 2020-06-26
 */
@Getter
@Setter
@ToString
public class CardMessage {

	private String bank;

	private String scheme;

	private String type;
}
