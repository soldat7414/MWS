package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.exceptions.ContactAlreadyExistException;
import com.Soldat.MWS.exceptions.ContactNotFoundException;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.repository.ContactRepo;
import com.Soldat.MWS.services.ContactService;
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
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepo repo;
    @Autowired
    ServiceE<PersonEntity> personService;

    @Override
    public ContactEntity add(ContactEntity entity) throws ContactAlreadyExistException {
        Optional<ContactEntity> contact = repo.findByContact(entity.getContact());
        if(contact.isPresent()) throw new ContactAlreadyExistException(
                "Такий контакт вже внесено до бази даних! ", contact.get().getId());
        else return repo.save(entity);
    }

    @Override
    public ContactEntity getById(long id) throws NotFoundException {
        Optional<ContactEntity> contact = repo.findById(id);
        if(contact.isPresent()) return contact.get();
        else throw new ContactNotFoundException("Такого контакту не знайдено.");
    }

    @Override
    public List<ContactEntity> getAll() {
        Iterable<ContactEntity> all = repo.findAll();
        List<ContactEntity> contacts = new ArrayList<>();
        all.forEach(contacts::add);
        return contacts;
    }

    @Override
    public long delete(long id) throws NotFoundException {
        ContactEntity contact = this.getById(id);
        repo.delete(contact);
        return id;
    }

    @Override
    public ContactEntity edit(long entityId, long fieldId, Functions function) throws NotFoundException {
        switch (function){
            case ADD_PERSON:{
                ContactEntity contact = this.getById(entityId);
                PersonEntity person = personService.getById(fieldId);
                contact.setPerson(person);
                return repo.save(contact);
            }
        }
        return null;
    }
}
