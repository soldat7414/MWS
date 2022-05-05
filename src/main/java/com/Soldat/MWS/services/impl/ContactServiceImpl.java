package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.AddressEntity;
import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.entity.models.contact_models.Contact;
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
    @Autowired
    ServiceE<OrganizationEntity> orgService;

    @Override
    public ContactEntity add(ContactEntity entity) throws ContactAlreadyExistException {
        Optional<ContactEntity> contact = repo.findByContact(entity.getContact());
        if(contact.isPresent()) throw new ContactAlreadyExistException(
                "Такий контакт вже внесено до бази даних! ", contact.get().getId());
        else return repo.save(entity);
    }

    @Override
    public ContactEntity getById(long id) throws ContactNotFoundException {
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
    public long delete(long id) throws ContactNotFoundException {
        ContactEntity contact = this.getById(id);
        repo.delete(contact);
        return id;
    }

    @Override
    public ContactEntity edit(long id, ContactEntity entity) throws ContactNotFoundException {
        ContactEntity contact = this.getById(id);
        if(entity.getContact()!=null)contact.setContact(entity.getContact());
        if(entity.getSpecies()!=null)contact.setSpecies(entity.getSpecies());
        if(entity.getPerson()!=null)contact.setPerson(entity.getPerson());
        if(entity.getOrganization()!=null)contact.setOrganization(entity.getOrganization());
        return repo.save(contact);
    }

    @Override
    public ContactEntity binding(long entityId, long linkId, Functions function) throws NotFoundException {
        ContactEntity contact = this.getById(entityId);
        switch (function){
            case ADD_PERSON:{
                PersonEntity person = personService.getById(linkId);
                contact.setPerson(person);
                break;
            }
            case ADD_ORGANIZATION:{
                OrganizationEntity org = orgService.getById(linkId);
                contact.setOrganization(org);
                break;
            }
        }
        return repo.save(contact);
    }
}
