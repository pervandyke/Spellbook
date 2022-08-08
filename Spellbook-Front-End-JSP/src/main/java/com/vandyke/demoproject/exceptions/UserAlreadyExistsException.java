package com.vandyke.demoproject.exceptions;

public class UserAlreadyExistsException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5443968447316696415L;

	public UserAlreadyExistsException() {}
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
