package com.mint.project.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mint.project.infrastructure.persistence.entity.CardEntity;

/**
 * @author Bruno Okafor 2020-06-26
 */
public interface CardRepository extends CrudRepository<CardEntity, Long> {

	Optional<CardEntity> findCardEntitiesByCardNumber(int cardNumber);

	@Query(value = "select c from CardEntity c")
	Page<CardEntity> getCardEntities(Pageable pageable);
}
