package com.Soldat.MWS.services;

import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.models.organization_models.Organization;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.exceptions.OrganizationAlreadyExistException;
import com.Soldat.MWS.exceptions.OrganizationNotFoundException;
import com.Soldat.MWS.services.utils.Functions;

import java.util.List;

public interface OrganizationService extends ServiceE<OrganizationEntity> {

    @Override
    OrganizationEntity add(OrganizationEntity entity) throws OrganizationAlreadyExistException;

    @Override
    OrganizationEntity getById(long id) throws OrganizationNotFoundException;

    @Override
    List<OrganizationEntity> getAll();

    @Override
    long delete(long id) throws OrganizationNotFoundException;

    @Override
    OrganizationEntity binding(long entityId, long fieldId, Functions function) throws NotFoundException;

    @Override
    OrganizationEntity edit(long id, OrganizationEntity entity) throws OrganizationNotFoundException;
}
