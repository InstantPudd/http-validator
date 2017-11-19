package com.github.instantpudd.validator;

import java.util.Optional;

/**
 * Allows for conditionally returning a particular HTTP status with a non-static
 * error message. Must be used in conjunction with
 * {@link ClientErrorExceptionHandler}. Example usage:
 * <code>
 *     Validator
 *			.returnStatus(ClientErrorStatusCode.BAD_REQUEST)
 *	 		.ifTrue(someExpression)
 *	 		.withErrorMessage("Your request needs some working on")
 *	 		.execute();
 * </code>
 * 
 * This class follows the step builder pattern.
 */
public class Validator {
	
	public static ValidationConditionStep returnStatus(ClientErrorStatusCode statusCode) {
		return new ValidationBuilder(statusCode);
	}

	public interface ValidationConditionStep {
		ValidationWithMessageStep always();
		ValidationWithMessageStep ifTrue(boolean expression);
		ValidationWithMessageStep ifFalse(boolean expression);
		ValidationWithMessageStep ifNull(Object thing);
		ValidationWithMessageStep ifNotNull(Object thing);
		ValidationWithMessageStep ifStringNullOrEmpty(String string);
		ValidationWithMessageStep ifEmpty(Optional optional);
		ValidationWithMessageStep ifNotEmpty(Optional optional);
	}

	public interface ValidationWithMessageStep {
        ValidationExecuteStep withNoErrorMessage();
		ValidationExecuteStep withErrorMessage(String errorMessage);
	}

	public interface ValidationExecuteStep {
		void execute();
	}

	private static class ValidationBuilder implements ValidationConditionStep, ValidationWithMessageStep, ValidationExecuteStep {
		private boolean shouldThrowException;
		private ClientErrorStatusCode statusToReturn;
		private String errorMessage;

		public ValidationBuilder(ClientErrorStatusCode statusToReturn) {
			this.statusToReturn = statusToReturn;
		}

		@Override
		public ValidationWithMessageStep always() {
			shouldThrowException = true;
			return this;
		}

		@Override
		public ValidationWithMessageStep ifTrue(boolean expression) {
			shouldThrowException = expression;
			return this;
		}

		@Override
		public ValidationWithMessageStep ifFalse(boolean expression) {
			shouldThrowException = ! expression;
			return this;
		}

		@Override
		public ValidationWithMessageStep ifNull(Object thing) {
			shouldThrowException = thing == null;
			return this;
		}

		@Override
		public ValidationWithMessageStep ifNotNull(Object thing) {
			shouldThrowException = thing != null;
			return this;
		}
		
		@Override
		public ValidationWithMessageStep ifStringNullOrEmpty(String string) {
			shouldThrowException = string == null || string.isEmpty();
			return this;
		}
		
		@Override
		public ValidationWithMessageStep ifEmpty(Optional optional) {
			shouldThrowException = ! optional.isPresent();
			return this;
		}
		
		@Override
		public ValidationWithMessageStep ifNotEmpty(Optional optional) {
			shouldThrowException = optional.isPresent();
			return this;
		}

		@Override
		public ValidationExecuteStep withErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		@Override
		public ValidationExecuteStep withNoErrorMessage() {
			return this;
		}

		@Override
		public void execute() {
			if(shouldThrowException) {
				throw new ClientErrorException(statusToReturn, errorMessage);
			}
		}
	}
}
