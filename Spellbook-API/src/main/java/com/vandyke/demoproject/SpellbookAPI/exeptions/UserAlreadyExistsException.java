package com.vandyke.demoproject.SpellbookAPI.exeptions;

public class UserAlreadyExistsException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2117045267012499371L;

	public UserAlreadyExistsException() {}
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
