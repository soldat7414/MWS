package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.address_models.Address;

import javax.persistence.*;

@Entity(name = "address")
public class AddressEntity {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String country;
    private String region;
    private String district;
    private String locality;
    private String street;
    private String building;
    private String coordinates;
    //connections
    //address of person
    @OneToOne
    @PrimaryKeyJoinColumn
    private PersonEntity person;
    @OneToOne
    @PrimaryKeyJoinColumn
    private OrganizationEntity organization;
    @OneToOne
    @PrimaryKeyJoinColumn
    private ObjectEntity object;



    //constructors
    public AddressEntity() {
    }
    public AddressEntity(String country, String region, String district, String locality, String street, String building) {
        this.country = country;
        this.region = region;
        this.district = district;
        this.locality = locality;
        this.street = street;
        this.building = building;
    }

    public Address toModel(){
        return Address.toModel(this);
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

    public void setBuilding(String builder) {
        this.building = builder;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public ObjectEntity getObject() {
        return object;
    }

    public void setObject(ObjectEntity object) {
        this.object = object;
    }
}
