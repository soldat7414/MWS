package com.Soldat.MWS.repository;

import com.Soldat.MWS.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepo extends CrudRepository<AddressEntity, Long> {

    Optional<AddressEntity> findByCountryAndRegionAndDistrictAndLocalityAndStreetAndBuilding
            (String country, String region, String district, String Locality, String street, String building);
}
