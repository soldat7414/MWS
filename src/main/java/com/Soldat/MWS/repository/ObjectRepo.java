package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.ObjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ObjectRepo extends CrudRepository<ObjectEntity, Long> {
    Optional<ObjectEntity> findByTitle (String title);
}
