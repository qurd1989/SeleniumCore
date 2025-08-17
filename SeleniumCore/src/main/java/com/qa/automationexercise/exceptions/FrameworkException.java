package com.qa.automationexercise.exceptions;

public class FrameworkException extends RuntimeException{

    /** * Custom exception class for handling framework-related errors.
     * This class extends RuntimeException and can be used to throw exceptions
     * related to framework operations, such as configuration issues or
     * initialization failures.
     *
     */
	public FrameworkException(String msg) {
		super(msg);
		
	}
}
