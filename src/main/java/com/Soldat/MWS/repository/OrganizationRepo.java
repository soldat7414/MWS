package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrganizationRepo extends CrudRepository<OrganizationEntity, Long> {
    Optional<OrganizationEntity> findByTitle (String title);
}
