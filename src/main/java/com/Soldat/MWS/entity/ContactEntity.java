package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.Contact;

import javax.persistence.*;
import java.util.List;

@Entity (name = "phone")
public class ContactEntity {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contact;
    private Species species;
    //connections
    //person who's is this number
    @ManyToOne
    @PrimaryKeyJoinColumn
    private PersonEntity person;

    //constructors
    public ContactEntity() {
    }

    public Contact toModel(){
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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
    public enum Species {
        PHONE_NUMBER ("phone number"),
        E_MAIL ("e-mail");

        private String title;

        Species(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
