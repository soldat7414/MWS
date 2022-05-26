package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.DocumentEntity;
import com.Soldat.MWS.exceptions.DocumentAlreadyExistException;
import com.Soldat.MWS.exceptions.DocumentNotFoundException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface DocumentService extends ServiceE<DocumentEntity> {

    @Override
    long add(DocumentEntity entity) throws DocumentAlreadyExistException;

    @Override
    DocumentEntity getById(long id) throws DocumentNotFoundException;

    @Override
    List<DocumentEntity> getAll();

    List<DocumentEntity> getBy(long entityId, Functions function) throws NotFoundException;

    @Override
    long delete(long id) throws DocumentNotFoundException;

    @Override
    DocumentEntity binding(long entityId, long linkId, Functions function) throws NotFoundException;

    @Override
    DocumentEntity edit(long id, DocumentEntity entity) throws DocumentNotFoundException;
}
