package com.Soldat.MWS.entity.models.object_models;


import com.Soldat.MWS.entity.ObjectEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.entity.models.organization_models.Organization;
import com.Soldat.MWS.entity.models.person_models.Person;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectNoLinks {

    //fields
    private long id;
    private String title;
    //links
//    private Address address;
//    private Organization customer;
//    private Organization builder;
//    private Person techSupervisor;
//    private Person authorSupervisor;
//    private List<Document> contract;
//    private List<Correspondence> correspondence;

    //to model
    public static ObjectNoLinks toModel(ObjectEntity entity) {
        ObjectNoLinks object = new ObjectNoLinks();
        if(entity.getId()!=0)object.setId(entity.getId());
        if(entity.getTitle()!=null)object.setTitle(entity.getTitle());
//        if(entity.getAddress()!=null)object.setAddress(entity.getAddress().toModel());
//        if(entity.getCustomer()!=null)object.setCustomer(entity.getCustomer().toModel());
//        if(entity.getBuilder()!=null)object.setBuilder(entity.getBuilder().toModel());
//        if(entity.getTechSupervisor()!=null)object.setTechSupervisor(entity.getTechSupervisor().toModel());
//        if(entity.getAuthorSupervisor()!=null)object.setAuthorSupervisor(entity.getAuthorSupervisor().toModel());
//        if(entity.getTitle()!=null)object.setContract(new ToModelList<Document, DocumentEntity>().parse(entity.getContract()));
//        if(entity.getTitle()!=null)object.setCorrespondence(new ToModelList<Correspondence, CorrespondenceEntity>().parse(entity.getCorrespondence()));
        return object;
    }

    public static List<ObjectNoLinks> toModelList (List<ObjectEntity> entities){
        return entities.stream().map(ObjectNoLinks::toModel).collect(Collectors.toList());
    }

    //constructor
    public ObjectNoLinks() {
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

}


