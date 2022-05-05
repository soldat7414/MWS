package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.entity.models.contact_models.Contact;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.ContactAlreadyExistException;
import com.Soldat.MWS.exceptions.ContactNotFoundException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface ContactService extends ServiceE<ContactEntity> {

    @Override
    ContactEntity add(ContactEntity entity) throws ContactAlreadyExistException;

    @Override
    ContactEntity getById(long id) throws ContactNotFoundException;

    @Override
    List<ContactEntity> getAll();

    @Override
    long delete(long id) throws ContactNotFoundException;

    @Override
    ContactEntity binding(long entityId, long fieldId, Functions function) throws NotFoundException;

    @Override
    ContactEntity edit(long id, ContactEntity entity) throws ContactNotFoundException;
}
