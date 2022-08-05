package com.vandyke.demoproject.exceptions;


public class SpellNotFoundException extends Exception {
    
    public SpellNotFoundException() {}
    
    public SpellNotFoundException(String message) {
        super(message);
    }
}
