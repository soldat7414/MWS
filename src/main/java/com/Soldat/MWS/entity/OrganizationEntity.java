package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.organization_models.Organization;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity (name = "organization")
public class OrganizationEntity {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String contrParty;

    //links
    //employees of organization include manager
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "organization")
    private List<PersonEntity> employees;

    //in case if this organization is builder
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "builder")
    private List<ObjectEntity> objectBuilder;

    //documents like the contract, license, permissions
    // TODO: 04.05.2022

    //in case if this organization is customer
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private List<ObjectEntity> objectCustomer;

    //from what organization the letter
    // TODO: 04.05.2022

    //to what organization the letter
    // TODO: 04.05.2022

    //address of organization
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "organization")
    private AddressEntity address;

    //contacts of organization
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private List<ContactEntity> contacts;

    //constructors
    public OrganizationEntity() {
    }

    //to model
    public Organization toModel (){
        return Organization.toModel(this);
    }

    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContrParty() {
        return contrParty;
    }

    public void setContrParty(String contrParty) {
        this.contrParty = contrParty;
    }

    public List<PersonEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<PersonEntity> employees) {
        this.employees = employees;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    public List<ObjectEntity> getObjectBuilder() {
        return objectBuilder;
    }

    public void setObjectBuilder(List<ObjectEntity> objectBuilder) {
        this.objectBuilder = objectBuilder;
    }

    public List<ObjectEntity> getObjectCustomer() {
        return objectCustomer;
    }

    public void setObjectCustomer(List<ObjectEntity> objectCustomer) {
        this.objectCustomer = objectCustomer;
    }
}