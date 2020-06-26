package com.mint.project.infrastructure.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.PersistenceException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mint.project.core.exception.CardNotFoundException;
import lombok.extern.log4j.Log4j2;

/**
 * @author Bruno Okafor 2020-06-26
 */
@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");

	@ExceptionHandler({CardNotFoundException.class})
	public ResponseEntity<Object> handleMessageCardNotFoundException(final CardNotFoundException ex) {

		return buildResponseEntity(
				createErrorResponse(
						ex.getMessage(),
						ex
				),
				HttpStatus.UNPROCESSABLE_ENTITY
		);
	}

	@ExceptionHandler({PersistenceException.class})
	public ResponseEntity<Object> handleMessagePersistenceException(final PersistenceException ex) {

		return buildResponseEntity(
				createErrorResponse(
						ex.getMessage(),
						ex
				),
				HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

	private static ResponseEntity<Object> buildResponseEntity(final Object response, final HttpStatus httpStatus) {
		return new ResponseEntity<>(response, httpStatus);
	}

	@ExceptionHandler(value = {IllegalArgumentException.class})
	ResponseEntity<Object> handleIllegalArgumentException(final Exception ex, final WebRequest request) {

		return buildResponseEntity(createErrorResponse(
				ex.getMessage(),
				ex
		), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(value = {DataAccessException.class, RuntimeException.class})
	ResponseEntity<Object> handleInternalException(final Exception ex, final WebRequest request) {

		return buildResponseEntity(createErrorResponse(
				ex.getMessage(),
				ex
		), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private GatewayError createErrorResponse(final String message, final Exception ex) {

		log.warn(
				"An exception {} has occurred",
				ex.getClass().getSimpleName(),
				ex
		);

		return GatewayError.of(message, "ERROR", LocalDateTime.now().format(format));
	}
}

