package com.mint.project.infrastructure.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mint.project.core.exception.CardNotFoundException;
import com.mint.project.core.shared.Card;
import com.mint.project.core.shared.CardPersistenceService;
import com.mint.project.infrastructure.persistence.converter.CardConverter;
import com.mint.project.infrastructure.persistence.entity.CardEntity;
import com.mint.project.infrastructure.persistence.repository.CardRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author Bruno Okafor 2020-06-26
 */
@RequiredArgsConstructor
@Service
public class CardPersistenceServiceImpl implements CardPersistenceService {

	private final CardRepository cardRepository;

	@Override
	public Optional<Card> findCardByCardNumber(final int cardNumber) {
		final Optional<CardEntity> optionalCardEntity = cardRepository.findCardEntitiesByCardNumber(cardNumber);

		return optionalCardEntity.map(CardConverter::convertToDomain);
	}

	@Override
	public void saveCard(final Card card) {
		try {
			final CardEntity cardEntity = CardConverter.convertToNewDatabaseEntity(card);

			cardRepository.save(cardEntity);
		} catch (final Exception e) {
			throw new PersistenceException("Error creating Card record", e);
		}
	}

	@Override
	public void updateCard(final Card card) {
		try {
			final CardEntity cardEntity = CardConverter.convertToExistingDatabaseEntity(card);

			cardRepository.save(cardEntity);
		} catch (final Exception e) {
			throw new PersistenceException("Error updating Card record", e);
		}
	}

	@Override
	public List<Card> getCards(final int start, final int limit) {
		final Pageable pageable = PageRequest.of(start, limit);

		final Page<CardEntity> cardEntities = cardRepository.getCardEntities(pageable);

		return cardEntities.toList().stream().map(CardConverter::convertToDomain).collect(Collectors.toList());
	}
}
