package com.pudd.validator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * If an uncaught ClientErrorExceptionHandler is thrown, this will map it
 * to a ResponseEntity with the exception's status code and message.
 * A bean of this class is required in order for Validator to work properly.
 */
@ControllerAdvice
public class ClientErrorExceptionHandler {
	@ExceptionHandler(value = ClientErrorException.class)
    public ResponseEntity<String> handleBadRequestException(
        ClientErrorException e
    ) {
        return new ResponseEntity(
			e.getMessage(),
			e.getStatusCode().getHttpStatus()
		);
    }
}
