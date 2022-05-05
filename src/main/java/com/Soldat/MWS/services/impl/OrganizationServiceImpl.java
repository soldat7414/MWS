package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.models.organization_models.Organization;
import com.Soldat.MWS.exceptions.AlreadyExistException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.exceptions.OrganizationAlreadyExistException;
import com.Soldat.MWS.exceptions.OrganizationNotFoundException;
import com.Soldat.MWS.repository.OrganizationRepo;
import com.Soldat.MWS.services.OrganizationService;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Scope("singleton")
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepo repo;

    @Override
    public OrganizationEntity add(OrganizationEntity entity) throws OrganizationAlreadyExistException {
        Optional<OrganizationEntity> org = repo.findByTitle(entity.getTitle());
        if(org.isPresent()) throw new OrganizationAlreadyExistException(
                "Організація з такою назвою вже внесена до бази даних.", org.get().getId());
        else return repo.save(entity);
    }

    @Override
    public OrganizationEntity getById(long id) throws OrganizationNotFoundException {
        Optional<OrganizationEntity> org = repo.findById(id);
        if(org.isEmpty()) throw new OrganizationNotFoundException(
                "Організація не знайдена");
        else return org.get();
    }

    @Override
    public List<OrganizationEntity> getAll() {
        Iterable<OrganizationEntity> orgs = repo.findAll();
        return StreamSupport.stream(orgs.spliterator(), false)
                .collect(Collectors.toList());

    }

    @Override
    public long delete(long id) throws OrganizationNotFoundException {
        repo.delete(this.getById(id));
        return id;
    }

    @Override
    public OrganizationEntity binding(long entityId, long fieldId, Functions function) throws NotFoundException {
        return null;
    }

    @Override
    public OrganizationEntity edit(long id, OrganizationEntity entity) throws OrganizationNotFoundException {
        OrganizationEntity org = this.getById(id);
        if(entity.getTitle()!=null)org.setTitle(entity.getTitle());
        if(entity.getContrParty()!=null)org.setContrParty(entity.getContrParty());
        //employees
        //contacts
        //address
        return repo.save(org);
    }
}
