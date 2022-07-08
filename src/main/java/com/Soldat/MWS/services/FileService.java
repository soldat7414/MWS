package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.DocumentEntity;
import com.Soldat.MWS.entity.FileEntity;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.FileAlreadyExistException;
import com.Soldat.MWS.exceptions.FileNotFoundException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface FileService extends ServiceE<FileEntity> {
    @Override
    FileEntity add(FileEntity entity) throws FileAlreadyExistException;

    @Override
    FileEntity getById(long id) throws FileNotFoundException;

    @Override
    List<FileEntity> getAll();

    List<FileEntity> getBy(long entityId, Functions function) throws NotFoundException;


    @Override
    long delete(long id) throws FileNotFoundException;

    @Override
    FileEntity binding(long entityId, long linkId, Functions function) throws NotFoundException;

    @Override
    FileEntity edit(long id, FileEntity entity) throws FileNotFoundException;
}
