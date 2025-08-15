package com.qa.automationexercise.exceptions;

public class BrowserExeption extends RuntimeException {
    /* * Custom exception class for handling browser-related errors.
 * This class extends RuntimeException and can be used to throw exceptions
 * related to browser operations, such as initialization failures or
 * unsupported browser types.
     */
	public BrowserExeption(String mesg) {
		super(mesg);
	}
}
