package com.vandyke.demoproject.exceptions;

public class UserNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1164023822414277757L;

	public UserNotFoundException() {}
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
