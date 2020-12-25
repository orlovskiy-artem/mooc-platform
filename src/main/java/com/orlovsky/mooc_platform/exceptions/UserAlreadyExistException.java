package com.orlovsky.mooc_platform.exceptions;


public class UserAlreadyExistException extends Exception{
    private String exceptionMessage;
    public UserAlreadyExistException(String s) {
        this.exceptionMessage=s;
    }
}
