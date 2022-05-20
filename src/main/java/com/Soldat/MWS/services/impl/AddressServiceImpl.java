package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.ObjectEntity;
import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.exceptions.AddressAlreadyExistException;
import com.Soldat.MWS.exceptions.AddressNotFoundException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.repository.AddressRepo;
import com.Soldat.MWS.services.AddressService;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo repo;


    private long containSameInDB (AddressEntity address){
        Optional<AddressEntity> result = repo.findByCountryAndRegionAndDistrictAndLocalityAndStreetAndBuilding(
                address.getCountry(), address.getRegion(), address.getDistrict(), address.getLocality(),
                address.getStreet(), address.getBuilding());
        return result.map(AddressEntity::getId).orElse(-1L);
    }

    public long add (AddressEntity address) throws AddressAlreadyExistException {
        long id = containSameInDB(address);
        if(id>0) throw new AddressAlreadyExistException("Таку адресу вже внесено в базу данихю", id);
        return repo.save(address).getId();
    }

    public AddressEntity getById (long id) throws AddressNotFoundException{
        Optional<AddressEntity> address = repo.findById(id);
        if(address.isPresent()) return address.get();
        else throw new AddressNotFoundException("Адреси з таким id ще не внесено до бази аних.");
    }

    public List<AddressEntity> getAll (){
        Iterable<AddressEntity> addresses = repo.findAll();
        List<AddressEntity> all = new ArrayList<>();
        addresses.forEach(all::add);
        return all;
    }

    @Override
    public long delete(long id) throws AddressNotFoundException {
        repo.delete(this.getById(id));
        return id;
    }

    @Override
    public AddressEntity edit(long id, AddressEntity entity) throws AddressNotFoundException {
        AddressEntity address = this.getById(id);
        if(entity.getCountry()!=null)address.setCountry(entity.getCountry());
        if(entity.getRegion()!=null)address.setRegion(entity.getRegion());
        if(entity.getDistrict()!=null)address.setDistrict(entity.getDistrict());
        if(entity.getLocality()!=null)address.setLocality(entity.getLocality());
        if(entity.getStreet()!=null)address.setStreet(entity.getStreet());
        if(entity.getBuilding()!=null)address.setBuilding(entity.getBuilding());
        if(entity.getCoordinates()!=null)address.setCoordinates(entity.getCoordinates());
        if(entity.getOrganizations()!=null)address.setOrganizations(entity.getOrganizations());
        if(entity.getPersons()!=null)address.setPersons(entity.getPersons());
        return repo.save(address);
    }

    @Override
    public AddressEntity binding(long addrId, long linkId, Functions function) throws NotFoundException {
        AddressEntity address = this.getById(addrId);
        switch (function){
//            case ADD_PERSON:{
//                PersonEntity person = personService.getById(linkId);
//                address.setPersons(person);
//                return repo.save(address);
//            }
//            case ADD_ORGANIZATION:{
//                OrganizationEntity org = orgService.getById(linkId);
//                address.setOrganization(org);
//                return repo.save(address);
//            }
//            case ADD_OBJECT:{
//                ObjectEntity obj = objService.getById(linkId);
//                address.setObject(obj);
//                return repo.save(address);
//            }
        }
        return null;
    }





}
