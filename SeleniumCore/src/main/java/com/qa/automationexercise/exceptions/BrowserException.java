package com.qa.automationexercise.exceptions;

public class BrowserException extends RuntimeException {
    /* * Custom exception class for handling browser-related errors.
 * This class extends RuntimeException and can be used to throw exceptions
 * related to browser operations, such as initialization failures or
 * unsupported browser types.
     */
	public BrowserException(String mesg) {
		super(mesg);
	}
}
