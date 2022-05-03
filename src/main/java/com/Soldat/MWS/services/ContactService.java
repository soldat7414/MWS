package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface ContactService extends ServiceE<ContactEntity> {

    @Override
    ContactEntity add(ContactEntity entity) throws AlreadyExistException;

    @Override
    ContactEntity getById(long id) throws NotFoundException;

    @Override
    List<ContactEntity> getAll();

    @Override
    long delete(long id) throws NotFoundException;

    @Override
    ContactEntity edit(long entityId, long fieldId, Functions function) throws NotFoundException;
}
