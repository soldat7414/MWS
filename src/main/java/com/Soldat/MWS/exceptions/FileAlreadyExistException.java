package com.Soldat.MWS.exceptions;

public class FileAlreadyExistException extends AlreadyExistException {

    public FileAlreadyExistException(String message, long id){
        super(message, id);
    }
}
