package com.pudd.validator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(value = ClientErrorException.class)
    public ResponseEntity<String> handleBadRequestException(
        ClientErrorException e
    ) {
        return new ResponseEntity<>( e.getMessage(), e.getStatusCode().getHttpStatus() );
    }
}
