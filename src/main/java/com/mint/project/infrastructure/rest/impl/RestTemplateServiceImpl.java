package com.mint.project.infrastructure.rest.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.mint.project.infrastructure.rest.RestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Bruno Okafor 2020-06-26
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class RestTemplateServiceImpl implements RestService {

	private final RestTemplate restTemplate;

	public <T> ResponseEntity<T> getForEntity(final Class<T> tClass, final String uriPath) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>("", headers);

		try {
			return restTemplate.exchange(uriPath, HttpMethod.GET, entity, tClass);

		} catch (final HttpStatusCodeException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch (final Exception ex) {
			log.warn("Exception trying to call to external gateway {}", ex.getMessage());
			return null;
		}
	}
}
