package com.mint.project.infrastructure.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-26
 */
@Getter
@Entity
@NoArgsConstructor
public class CardEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, unique = true)
	private String cardNumber;

	@Column(nullable = false)
	private String scheme;

	@Column(nullable = false)
	private String type;

	private String bank;

	private long totalRequest;

	public CardEntity(final long id, final String bank, final String cardNumber, final String scheme, final long totalRequest, final String type) {
		this.id = id;
		this.bank = bank;
		this.cardNumber = cardNumber;
		this.scheme = scheme;
		this.totalRequest = totalRequest;
		this.type = type;
	}

	public CardEntity(final String bank, final String cardNumber, final String scheme, final long totalRequest, final String type) {
		this.bank = bank;
		this.cardNumber = cardNumber;
		this.scheme = scheme;
		this.totalRequest = totalRequest;
		this.type = type;
	}
}
