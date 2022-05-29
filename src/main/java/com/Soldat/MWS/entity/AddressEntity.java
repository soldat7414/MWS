package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.entity.supporting_classes.User;

import javax.persistence.*;
import java.util.List;

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

    //from what account was created organization
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
    //connections
    //address of person
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "address")
    private List<PersonEntity> persons;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "address")
    private List<OrganizationEntity> organizations;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "address")
    private List<ObjectEntity> objects;



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

    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }

    public List<OrganizationEntity> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationEntity> organizations) {
        this.organizations = organizations;
    }

    public List<ObjectEntity> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectEntity> objects) {
        this.objects = objects;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
