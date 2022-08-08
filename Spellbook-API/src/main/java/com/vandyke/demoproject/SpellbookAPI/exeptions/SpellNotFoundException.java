package com.vandyke.demoproject.SpellbookAPI.exeptions;


public class SpellNotFoundException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5844090597238095773L;

	public SpellNotFoundException() {}
    
    public SpellNotFoundException(String message) {
        super(message);
    }
}
