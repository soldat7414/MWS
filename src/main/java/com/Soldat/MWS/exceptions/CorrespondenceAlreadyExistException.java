package com.Soldat.MWS.exceptions;

public class CorrespondenceAlreadyExistException extends AlreadyExistException {
    public CorrespondenceAlreadyExistException(String message, long id) {
        super(message, id);
    }
}
