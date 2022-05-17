package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.DocumentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocumentRepo extends CrudRepository<DocumentEntity, Long> {
    Optional<DocumentEntity> findByTitleAndDescriptionAndDocType (
            String title, String description, String docType);
}
