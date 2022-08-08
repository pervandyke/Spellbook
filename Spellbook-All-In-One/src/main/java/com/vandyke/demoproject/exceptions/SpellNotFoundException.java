package com.vandyke.demoproject.exceptions;


public class SpellNotFoundException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7782233228893315255L;

	public SpellNotFoundException() {}
    
    public SpellNotFoundException(String message) {
        super(message);
    }
}
