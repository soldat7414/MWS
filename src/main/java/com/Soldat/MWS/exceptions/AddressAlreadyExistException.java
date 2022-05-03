package com.Soldat.MWS.exceptions;

public class AddressAlreadyExistException extends AlreadyExistException {
    public AddressAlreadyExistException(String message, long id) {super(message, id);
    }
}
