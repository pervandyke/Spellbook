package com.vandyke.demoproject.SpellbookAPI.exeptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {}
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
