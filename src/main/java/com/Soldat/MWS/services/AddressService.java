package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.exceptions.AddressAlreadyExistException;
import com.Soldat.MWS.exceptions.AddressNotFoundException;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface AddressService extends ServiceE<AddressEntity> {

    @Override
    AddressEntity add(AddressEntity entity) throws AddressAlreadyExistException;

    @Override
    AddressEntity getById(long id) throws AddressNotFoundException;

    @Override
    List<AddressEntity> getAll();

    @Override
    long delete(long id) throws AddressNotFoundException;

    @Override
    AddressEntity binding(long entityId, long fieldId, Functions function) throws NotFoundException;

    @Override
    AddressEntity edit(long id, AddressEntity entity) throws AddressNotFoundException;
}
