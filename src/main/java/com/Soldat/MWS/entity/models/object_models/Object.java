package com.Soldat.MWS.entity.models.object_models;


import com.Soldat.MWS.entity.ObjectEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.entity.models.organization_models.Organization;
import com.Soldat.MWS.entity.models.person_models.Person;

import java.util.List;
import java.util.stream.Collectors;

public class Object {

    //fields
    private long id;
    private String title;
    //links
    private Address address;
    private Organization customer;
    private Organization builder;
    private Person techSupervisor;
    private Person authorSupervisor;
//    private List<Document> contract;
//    private List<Correspondence> correspondence;

    //to model
    public static Object toModel(ObjectEntity entity) {
        Object object = new Object();
        if(entity.getId()!=0)object.setId(entity.getId());
        if(entity.getTitle()!=null)object.setTitle(entity.getTitle());
        if(entity.getAddress()!=null)object.setAddress(entity.getAddress().toModel());
        if(entity.getCustomer()!=null)object.setCustomer(entity.getCustomer().toModel());
        if(entity.getBuilder()!=null)object.setBuilder(entity.getBuilder().toModel());
        if(entity.getTechSupervisor()!=null)object.setTechSupervisor(entity.getTechSupervisor().toModel());
        if(entity.getAuthorSupervisor()!=null)object.setAuthorSupervisor(entity.getAuthorSupervisor().toModel());
//        if(entity.getTitle()!=null)object.setContract(new ToModelList<Document, DocumentEntity>().parse(entity.getContract()));
//        if(entity.getTitle()!=null)object.setCorrespondence(new ToModelList<Correspondence, CorrespondenceEntity>().parse(entity.getCorrespondence()));
        return object;
    }

    public static List<Object> toModelList (List<ObjectEntity> entities){
        return entities.stream().map(ObjectEntity::toModel).collect(Collectors.toList());
    }

    //constructor
    public Object() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Organization getCustomer() {
        return customer;
    }

    public void setCustomer(Organization customer) {
        this.customer = customer;
    }

    public Organization getBuilder() {
        return builder;
    }

    public void setBuilder(Organization builder) {
        this.builder = builder;
    }

    public Person getTechSupervisor() {
        return techSupervisor;
    }

    public void setTechSupervisor(Person techSupervisor) {
        this.techSupervisor = techSupervisor;
    }

    public Person getAuthorSupervisor() {
        return authorSupervisor;
    }

    public void setAuthorSupervisor(Person authorSupervisor) {
        this.authorSupervisor = authorSupervisor;
    }
}


