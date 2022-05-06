package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.object_models.Object;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "object")
public class ObjectEntity {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    //    private String project;
    //connections
    //address of object
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "object")
    private AddressEntity address;
    //customer of object
    @ManyToOne
    @PrimaryKeyJoinColumn
    private OrganizationEntity customer;
    //builder of object
    @ManyToOne
    @PrimaryKeyJoinColumn
    private OrganizationEntity builder;
    //technical supervision
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PersonEntity techSupervisor;
    //author supervision
    @OneToOne(cascade = CascadeType.ALL)
    private PersonEntity authorSupervisor;

    //contract and additional agreements
// TODO: 05.05.2022
    //correspondence about the object
// TODO: 05.05.2022

    //constructors
    public ObjectEntity() {
    }

    //toModel
    public Object toModel(){
        return Object.toModel(this);
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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public OrganizationEntity getCustomer() {
        return customer;
    }

    public void setCustomer(OrganizationEntity customer) {
        this.customer = customer;
    }

    public OrganizationEntity getBuilder() {
        return builder;
    }

    public void setBuilder(OrganizationEntity builder) {
        this.builder = builder;
    }

    public PersonEntity getTechSupervisor() {
        return techSupervisor;
    }

    public void setTechSupervisor(PersonEntity techSupervisor) {
        this.techSupervisor = techSupervisor;
    }

    public PersonEntity getAuthorSupervisor() {
        return authorSupervisor;
    }

    public void setAuthorSupervisor(PersonEntity authorSupervisor) {
        this.authorSupervisor = authorSupervisor;
    }
}
