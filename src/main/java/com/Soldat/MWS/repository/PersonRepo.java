package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepo extends CrudRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByFirstNameAndLastNameAndSurname (String fistName, String lastName, String Surname);
}
