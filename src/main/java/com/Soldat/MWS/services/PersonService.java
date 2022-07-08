package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.entity.models.person_models.Person;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.exceptions.PersonAlreadyExistException;
import com.Soldat.MWS.exceptions.PersonNotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface PersonService extends ServiceE<PersonEntity> {
    @Override
    PersonEntity add(PersonEntity entity) throws PersonAlreadyExistException;

    @Override
    PersonEntity getById(long id) throws PersonNotFoundException;

    @Override
    List<PersonEntity> getAll();

    @Override
    long delete(long id) throws PersonNotFoundException;

    @Override
    PersonEntity binding(long entityId, long fieldId, Functions function) throws NotFoundException;

    @Override
    PersonEntity edit(long id, PersonEntity entity) throws PersonNotFoundException;
}
