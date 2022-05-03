package com.Soldat.MWS.services.impl;

import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.exceptions.NotFoundException;
import com.Soldat.MWS.exceptions.PersonAlreadyExistException;
import com.Soldat.MWS.exceptions.PersonNotFoundException;
import com.Soldat.MWS.repository.PersonRepo;
import com.Soldat.MWS.services.PersonService;
import com.Soldat.MWS.services.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo repo;

    public PersonServiceImpl() {
    }

    private long containSameInDB(PersonEntity person) {
        Optional<PersonEntity> result = repo.findByFirstNameAndLastNameAndSurname(person.getFirstName(),
                person.getLastName(), person.getSurname());
        return result.map(PersonEntity::getId).orElse(-1L);
    }

    @Override
    public PersonEntity add(PersonEntity person) throws PersonAlreadyExistException {
        long id = containSameInDB(person);
        if (id > 0) throw new PersonAlreadyExistException("Така людина вже записана до бази даних.", id);
        return repo.save(person);
    }

    @Override
    public PersonEntity getById(long id) throws PersonNotFoundException {
        Optional<PersonEntity> person = repo.findById(id);
        if (person.isPresent()) return person.get();
        else throw new PersonNotFoundException("Людини за таким id не знайдено.");
    }

    @Override
    public List<PersonEntity> getAll() {
        Iterable<PersonEntity> people = repo.findAll();
        List<PersonEntity> all = new ArrayList<>();
        people.forEach(all::add);
        return all;
    }

    @Override
    public long delete(long id) throws PersonNotFoundException {
        repo.delete(this.getById(id));
        return id;
    }

    @Override
    public PersonEntity edit(long entityId, long fieldId, Functions function) throws NotFoundException {
        return null;
    }

}