package com.Soldat.MWS.exceptions;

public class ObjectAlreadyExistException extends AlreadyExistException {
    public ObjectAlreadyExistException (String message, long id){super(message, id);}
}
