package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.ObjectEntity;
import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.exceptions.*;
import com.Soldat.MWS.repository.ObjectRepo;
import com.Soldat.MWS.services.ObjectService;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope("singleton")
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    private ObjectRepo repo;
    @Autowired
    private ServiceE<OrganizationEntity> orgService;
    @Autowired
    private ServiceE<PersonEntity> personService;
    @Autowired
    private ServiceE<AddressEntity> addressService;

    @Override
    public ObjectEntity add(ObjectEntity entity) throws ObjectAlreadyExistException {
        Optional<ObjectEntity> obj = repo.findByTitle(entity.getTitle());
        if (obj.isPresent()) throw new ObjectAlreadyExistException(
                "Об'єкт з такою назвою вже внесена до бази даних.", obj.get().getId());
        else return repo.save(entity);
    }

    @Override
    public ObjectEntity getById(long id) throws ObjectNotFoundException {
        Optional<ObjectEntity> obj = repo.findById(id);
        if (obj.isEmpty()) throw new ObjectNotFoundException(
                "Об'єкт не знайдена");
        else return obj.get();
    }

    @Override
    public List<ObjectEntity> getAll() {
        Iterable<ObjectEntity> objects = repo.findAll();
        return StreamSupport.stream(objects.spliterator(), false)
                .collect(Collectors.toList());

    }

    @Override
    public long delete(long id) throws ObjectNotFoundException {
        repo.delete(this.getById(id));
        return id;
    }

    @Override
    public ObjectEntity binding(long entityId, long linkId, Functions function) throws NotFoundException {
        ObjectEntity obj = this.getById(entityId);
        switch (function) {
            case ADD_CUSTOMER: {
                OrganizationEntity org = orgService.getById(linkId);
                obj.setCustomer(org);
                break;
            }
            case ADD_BUILDER: {
                OrganizationEntity org = orgService.getById(linkId);
                obj.setBuilder(org);
                break;
            }
            case ADD_TECH_SUP: {
                PersonEntity pers = personService.getById(linkId);
                obj.setTechSupervisor(pers);
                break;
            }
            case ADD_AUTH_SUP: {
                PersonEntity pers = personService.getById(linkId);
                obj.setAuthorSupervisor(pers);
                break;
            }
            case ADD_ADDRESS: {
                AddressEntity addr = addressService.getById(linkId);
                obj.setAddress(addr);
                break;
            }
        }
        obj.setLastChange(new Date());
        return repo.save(obj);
    }

    @Override
    public ObjectEntity edit(long id, ObjectEntity entity) throws ObjectNotFoundException {
        ObjectEntity obj = this.getById(id);
        if (entity.getTitle() != null) obj.setTitle(entity.getTitle());
        if(entity.getBeginBuilding()!=null)obj.setBeginBuilding(entity.getBeginBuilding());
        if(entity.getEndBuilding()!=null)obj.setEndBuilding(entity.getEndBuilding());
       // if(entity.getAddObject()!=null)obj.setAddObject(entity.getAddObject());
        obj.setLastChange(new Date());
        //employees
        //contacts
        //address
        return repo.save(obj);
    }
}
