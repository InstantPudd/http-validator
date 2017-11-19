package com.github.instantpudd.validator;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {
	
	@Test(expected=ClientErrorException.class)
	public void always_shouldThrowException() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.always()
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void returnStatus_shouldThrowExceptionWithCorrectHttpStatus() {
		try {
			Validator
				.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
				.always()
				.withNoErrorMessage()
				.execute();
		} catch(ClientErrorException e) {
			Assert.assertEquals(
				ClientErrorStatusCode.IM_A_TEAPOT.getHttpStatus(),
				e.getStatusCode().getHttpStatus()
			);
		}
	}
	
	@Test(expected=ClientErrorException.class)
	public void ifTrue_shouldThrowExceptionIfTrue() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifTrue(true)
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void ifTrue_shouldNotThrowExceptionIfFalse() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifTrue(false)
			.withNoErrorMessage()
			.execute();
	}
	
	@Test(expected=ClientErrorException.class)
	public void ifFalse_whouldThrowExceptionIfFalse() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifFalse(false)
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void ifFalse_shouldNotThrowExceptionIfTrue() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifFalse(true)
			.withNoErrorMessage()
			.execute();
	}
	
	@Test(expected=ClientErrorException.class)
	public void ifNull_shouldThrowExceptionIfNull() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifNull(null)
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void ifNull_shouldNotThrowExceptionIfNotNull() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifNull(32)
			.withNoErrorMessage()
			.execute();
	}
	
	@Test(expected = ClientErrorException.class)
	public void ifNullOrEmptyString_shouldThrowExceptionIfNull() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifStringNullOrEmpty(null)
			.withNoErrorMessage()
			.execute();
	}
	
	@Test(expected = ClientErrorException.class)
	public void ifNullOrEmptyString_shouldThrowExceptionIfEmptyString() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifStringNullOrEmpty("")
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void ifNullOrEmptyString_shouldNotThrowExceptionIfNonEmptyString() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifStringNullOrEmpty("short and stout")
			.withNoErrorMessage()
			.execute();
	}
	
	@Test(expected = ClientErrorException.class)
	public void ifEmpty_shouldThrowExceptionIfEmpty() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifEmpty(Optional.empty())
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void ifEmpty_shouldNotThrowExceptionIfNotEmpty() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifEmpty(Optional.of("short and stout"))
			.withNoErrorMessage()
			.execute();
	}
	
	@Test(expected = ClientErrorException.class)
	public void ifNotEmpty_shouldThrowExceptionIfNotEmpty() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifNotEmpty(Optional.of("short and stout"))
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void ifNotEmpty_shouldNotThrowExceptionIfEmpty() {
		Validator
			.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
			.ifNotEmpty(Optional.empty())
			.withNoErrorMessage()
			.execute();
	}
	
	@Test
	public void withErrorMessage_exceptionShouldIncludeMessage() {
		try {
			Validator
				.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
				.always()
				.withErrorMessage("short and stout")
				.execute();
		} catch(ClientErrorException e) {
			Assert.assertEquals(
				"short and stout",
				e.getMessage()
			);
		}
	}
	
	@Test
	public void withNoErrorMessage_exceptionShouldNotIncludeMessage() {
		try {
			Validator
				.returnStatus(ClientErrorStatusCode.IM_A_TEAPOT)
				.always()
				.withNoErrorMessage()
				.execute();
		} catch(ClientErrorException e) {
			Assert.assertTrue(
				e.getMessage() == null ||
				e.getMessage().isEmpty()
			);
		}
	}
}