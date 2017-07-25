package com.github.instantpudd.validator;

/**
 * Represents an 4xx http status code and corresponding error message
 */
public class ClientErrorException extends RuntimeException{

	private ClientErrorStatusCode statusCode;

	public ClientErrorStatusCode getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(ClientErrorStatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public ClientErrorException(ClientErrorStatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public ClientErrorException(ClientErrorStatusCode statusCode, String errorMessage) {
		super(errorMessage);
		this.statusCode = statusCode;
	}
}
