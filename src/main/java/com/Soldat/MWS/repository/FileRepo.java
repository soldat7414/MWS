package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FileRepo extends CrudRepository<FileEntity, Long> {
    Optional<FileEntity> findByTitle (String title);
}
