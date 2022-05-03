package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.exceptions.AddressAlreadyExistException;
import com.Soldat.MWS.exceptions.AddressNotFoundException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.repository.AddressRepo;
import com.Soldat.MWS.services.AddressService;
import com.Soldat.MWS.services.ServiceE;
import com.Soldat.MWS.services.utils.Functions;
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
    @Autowired
    private ServiceE<PersonEntity> personServiceEImpl;

    private long containSameInDB (AddressEntity address){
        Optional<AddressEntity> result = repo.findByCountryAndRegionAndDistrictAndLocalityAndStreetAndBuilding(
                address.getCountry(), address.getRegion(), address.getDistrict(), address.getLocality(),
                address.getStreet(), address.getBuilding());
        return result.map(AddressEntity::getId).orElse(-1L);
    }

    public AddressEntity add (AddressEntity address) throws AddressAlreadyExistException {
        long id = containSameInDB(address);
        if(id>0) throw new AddressAlreadyExistException("Таку адресу вже внесено в базу данихю", id);
        return repo.save(address);
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
    public long delete(long id) throws NotFoundException {
        repo.delete(this.getById(id));
        return id;
    }

    @Override
    public AddressEntity edit(long entityId, long fieldId, Functions function) throws NotFoundException {

        switch (function){
            case ADD_PERSON:{
                AddressEntity address = this.getById(entityId);
                PersonEntity person = personServiceEImpl.getById(fieldId);
                address.setPerson(person);
                return repo.save(address);
            }
        }
        return null;
    }





}
