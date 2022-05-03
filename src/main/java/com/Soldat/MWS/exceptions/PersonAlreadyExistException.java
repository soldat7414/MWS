package com.Soldat.MWS.exceptions;

public class PersonAlreadyExistException  extends AlreadyExistException {

    public PersonAlreadyExistException (String message, long id){
        super(message, id);
    }
}
