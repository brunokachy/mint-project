package com.mint.project.infrastructure.rest;

import org.springframework.http.ResponseEntity;

/**
 * @author Bruno Okafor 2020-06-26
 */
public interface RestService {

	<T> ResponseEntity<T> getForEntity(Class<T> clazz, String uriPath);
}
