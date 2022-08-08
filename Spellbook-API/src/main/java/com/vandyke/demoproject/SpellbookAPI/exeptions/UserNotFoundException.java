package com.vandyke.demoproject.SpellbookAPI.exeptions;

public class UserNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2616250612496166568L;

	public UserNotFoundException() {}
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
