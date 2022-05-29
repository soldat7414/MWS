package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.object_models.Object;
import com.Soldat.MWS.entity.supporting_classes.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "object")
public class ObjectEntity {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date beginBuilding;
    private Date endBuilding;
    private Date addObject;
    private Date lastChange;
    //from what account was created organization
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
    //    private String project;
    //connections
    //address of object
    @ManyToOne
    @PrimaryKeyJoinColumn
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
    @ManyToOne
    private PersonEntity techSupervisor;

    //author supervision
    @ManyToOne(cascade = CascadeType.ALL)
    private PersonEntity authorSupervisor;

    //contract and additional agreements
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "object")
    private List<DocumentEntity> documents;


    //correspondence about the object
// TODO: 05.05.2022

    //constructors
    public ObjectEntity() {
        addObject = new Date();
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

    public Date getBeginBuilding() {
        return beginBuilding;
    }

    public void setBeginBuilding(Date beginBuilding) {
        this.beginBuilding = beginBuilding;
    }

    public Date getEndBuilding() {
        return endBuilding;
    }

    public void setEndBuilding(Date endBuilding) {
        this.endBuilding = endBuilding;
    }

    public Date getAddObject() {
        return addObject;
    }
//
//    public void setAddObject(Date addObject) {
//        this.addObject = addObject;
//    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
