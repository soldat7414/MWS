package com.Soldat.MWS.exceptions;

public class DocumentAlreadyExistException extends AlreadyExistException {
    public DocumentAlreadyExistException (String message, long id){
        super (message, id);
    }
}
