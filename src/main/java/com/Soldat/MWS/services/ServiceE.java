package com.Soldat.MWS.services;

import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.rmi.AlreadyBoundException;
import java.util.List;

public interface ServiceE<E> {

    E add (E entity) throws AlreadyExistException;
    E getById (long id) throws NotFoundException;
    List<E> getAll ();
    long delete (long id) throws NotFoundException;
    E binding(long entityId, long fieldId, Functions function) throws NotFoundException;
    E edit (long id, E entity) throws NotFoundException;

}
