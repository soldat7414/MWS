package com.Soldat.MWS.entity.models;

import com.Soldat.MWS.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    //fields
    private long id;
    private String firstName;
    private String lastName;
    private String surname;
    private String position;
    private Address address;

    //to model
    //@Override
    public static Person toModel(PersonEntity personEntity) {
        Person person = new Person();
        person.setId(personEntity.getId());
        person.setFirstName(personEntity.getFirstName());
        person.setLastName(personEntity.getLastName());
        person.setSurname(personEntity.getSurname());
        person.setPosition(personEntity.getPosition());
        if(personEntity.getAddress()!=null)person.setAddress(personEntity.getAddress().toModel());
        return person;
    }

    public static List<Person> toModelList(List<PersonEntity> entities) {
        return entities.stream().map(PersonEntity::toModel).collect(Collectors.toList());
    }

    //constructors
    public Person() {
    }
    //getters and setters

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }




}
