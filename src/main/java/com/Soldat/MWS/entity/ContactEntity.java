package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.contact_models.Contact;
import com.Soldat.MWS.entity.models.organization_models.Organization;
import com.Soldat.MWS.entity.supporting_classes.User;

import javax.persistence.*;

@Entity(name = "contact")
public class ContactEntity {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contact;
    private String species;
    //from what account was created contact
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    //links
    //person who's is this contact
    @ManyToOne
    @PrimaryKeyJoinColumn
    private PersonEntity person;
    //organization who's is this contact
    @ManyToOne
    @PrimaryKeyJoinColumn
    private OrganizationEntity organization;

    //constructors
    public ContactEntity() {
    }

    public Contact toModel() {
        return Contact.toModel(this);
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

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
