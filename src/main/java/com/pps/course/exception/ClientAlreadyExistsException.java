package com.pps.course.exception;

public class ClientAlreadyExistsException extends Exception{
    public ClientAlreadyExistsException(String message){
        super(message);
    }
}
