package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepo extends CrudRepository<ContactEntity, Long> {
    Optional<ContactEntity> findByContact (String contact);
    Iterable<ContactEntity> findByOrganization (OrganizationEntity organization);
    Iterable<ContactEntity> findByPerson (PersonEntity person);
}
