package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.ObjectEntity;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.exceptions.ObjectAlreadyExistException;
import com.Soldat.MWS.exceptions.ObjectNotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface ObjectService extends ServiceE<ObjectEntity> {
    @Override
    ObjectEntity add(ObjectEntity entity) throws ObjectAlreadyExistException;

    @Override
    ObjectEntity getById(long id) throws ObjectNotFoundException;

    @Override
    List<ObjectEntity> getAll();

    @Override
    long delete(long id) throws ObjectNotFoundException;

    @Override
    ObjectEntity binding(long entityId, long fieldId, Functions function) throws NotFoundException;

    @Override
    ObjectEntity edit(long id, ObjectEntity entity) throws ObjectNotFoundException;
}
