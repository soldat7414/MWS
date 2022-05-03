package com.Soldat.MWS.exceptions;


public class AlreadyExistException extends Exception {
    long id;
    AlreadyExistException(String message, long id){
        super(message);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
