package com.Soldat.MWS.exceptions;

public class ContactAlreadyExistException extends AlreadyExistException {
    public ContactAlreadyExistException(String message, long id) {
        super(message, id);
    }
}
