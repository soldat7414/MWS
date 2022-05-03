package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface AddressService extends ServiceE<AddressEntity> {

    @Override
    AddressEntity add(AddressEntity entity) throws AlreadyExistException;

    @Override
    AddressEntity getById(long id) throws NotFoundException;

    @Override
    List<AddressEntity> getAll();

    @Override
    long delete(long id) throws NotFoundException;

    @Override
    AddressEntity edit(long entityId, long fieldId, Functions function) throws NotFoundException;
}
