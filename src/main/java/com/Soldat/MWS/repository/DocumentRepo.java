package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.DocumentEntity;
import com.Soldat.MWS.entity.ObjectEntity;
import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.PersonEntity;
import com.Soldat.MWS.entity.models.object_models.Object;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocumentRepo extends CrudRepository<DocumentEntity, Long> {
    Optional<DocumentEntity> findByTitleAndDescriptionAndWhatDocument (
            String title, String description, String whatDocument);
    Iterable<DocumentEntity> findByPerson (PersonEntity person);
    Iterable<DocumentEntity> findByOrganization (OrganizationEntity organization);
    Iterable<DocumentEntity> findByObject (ObjectEntity object);

}
