package com.vandyke.demoproject.SpellbookAPI.exeptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {}
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
