package com.Soldat.MWS.exceptions;

public class OrganizationAlreadyExistException extends AlreadyExistException {
    public OrganizationAlreadyExistException (String message, long id){super(message, id);}
}
