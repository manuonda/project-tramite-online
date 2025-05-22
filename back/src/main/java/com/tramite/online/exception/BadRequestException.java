package com.tramite.online.exception;



public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }

}
