package com.Soldat.MWS.entity.models.address_models;


import com.Soldat.MWS.entity.AddressEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Address{
    //fields
    private long id;
    private String country;
    private String region;
    private String district;
    private String locality;
    private String street;
    private String building;
    private String coordinates;

    //to model
    public static Address toModel(AddressEntity addressEntity){
        Address address = new Address();
        address.setId(addressEntity.getId());
        address.setCountry(addressEntity.getCountry());
        address.setRegion(addressEntity.getRegion());
        address.setDistrict(addressEntity.getDistrict());
        address.setLocality(addressEntity.getLocality());
        address.setStreet(addressEntity.getStreet());
        address.setBuilding(addressEntity.getBuilding());
        address.setCoordinates(addressEntity.getCoordinates());
        return address;
    }

    public static List<Address> toModelList (List<AddressEntity> entities){
        return entities.stream().map(AddressEntity::toModel).collect(Collectors.toList());
    }

    //constructor
    public Address() {
    }
    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }


    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", locality='" + locality + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}
