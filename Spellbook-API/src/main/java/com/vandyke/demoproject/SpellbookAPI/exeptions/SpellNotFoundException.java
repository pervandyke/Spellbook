package com.vandyke.demoproject.SpellbookAPI.exeptions;


public class SpellNotFoundException extends Exception {
    
    public SpellNotFoundException() {}
    
    public SpellNotFoundException(String message) {
        super(message);
    }
}
