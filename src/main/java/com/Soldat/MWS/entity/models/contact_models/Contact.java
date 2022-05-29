package com.Soldat.MWS.entity.models.contact_models;


import com.Soldat.MWS.entity.ContactEntity;
import com.Soldat.MWS.entity.supporting_classes.User;

import java.util.List;
import java.util.stream.Collectors;

public class Contact {
    //fields
    private long id;
    private String contact;
    private String species;
    private User author;

    public static Contact toModel(ContactEntity entity) {
        Contact contact = new Contact();
        contact.setId(entity.getId());
        contact.setContact(entity.getContact());
        contact.setSpecies(entity.getSpecies());
        if(entity.getAuthor()!=null)contact.setAuthor(entity.getAuthor());

        return contact;
    }

    public static List<Contact> toModelList (List<ContactEntity> entities){
        return entities.stream().map(ContactEntity::toModel).collect(Collectors.toList());
    }
    //constructor
    public Contact() {
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
