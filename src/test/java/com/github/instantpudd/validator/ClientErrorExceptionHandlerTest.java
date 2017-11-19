package com.github.instantpudd.validator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class ClientErrorExceptionHandlerTest {
	
	private final ClientErrorExceptionHandler clientErrorExceptionHandler = new ClientErrorExceptionHandler();
	
	@Test
	public void shouldMapClientErrorException() {
		ResponseEntity<String> response = clientErrorExceptionHandler
			.handleBadRequestException(
				new ClientErrorException(
					ClientErrorStatusCode.IM_A_TEAPOT,
					"short and stout"
				)
			);
		Assert.assertTrue(
			response
				.getStatusCode()
				.equals(
					ClientErrorStatusCode.IM_A_TEAPOT.getHttpStatus()
				)
		);
		Assert.assertTrue(
			response.getBody().equals("short and stout")
		);
	}
}
