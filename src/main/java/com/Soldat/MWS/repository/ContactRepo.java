package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepo extends CrudRepository<ContactEntity, Long> {
    Optional<ContactEntity> findByContact (String contact);
}
