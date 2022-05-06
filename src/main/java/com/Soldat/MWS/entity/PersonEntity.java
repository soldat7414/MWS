package com.Soldat.MWS.entity;


import com.Soldat.MWS.entity.models.person_models.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity (name = "person")
public class PersonEntity {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String surname;
    private String position;

    //connection
    //address of person
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private AddressEntity address = new AddressEntity();
    //phone numbers
    @OneToMany(cascade=CascadeType.ALL, mappedBy="person")
    private List<ContactEntity> phoneNumbers = new ArrayList<>();
    //organization, where person works
    @ManyToOne
    @PrimaryKeyJoinColumn
    private OrganizationEntity organization;
    //in case if person is technical supervisor
    @OneToMany(cascade=CascadeType.ALL, mappedBy="techSupervisor")
    private List<ObjectEntity> objectsTechSup = new ArrayList<>();
    //in case if person is author supervisor
    @OneToMany(cascade=CascadeType.ALL, mappedBy="authorSupervisor")
    private List<ObjectEntity> objectAuthSup = new ArrayList<>();


    //constructors
    public PersonEntity() {
    }

    public PersonEntity(String firstName, String lastName, String surname, String position, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
        this.position = position;
    }

    //equals method for DocumentEntity

    @Override
    public String toString() {
        return "PersonEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Person toModel(){
        return Person.toModel(this);
    }



    //getters and setters

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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity addressEntity) {
        this.address = addressEntity;
    }

    public List<ContactEntity> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<ContactEntity> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public List<ObjectEntity> getObjectsTechSup() {
        return objectsTechSup;
    }

    public void setObjectsTechSup(List<ObjectEntity> objectsTechSup) {
        this.objectsTechSup = objectsTechSup;
    }

    public List<ObjectEntity> getObjectAuthSup() {
        return objectAuthSup;
    }

    public void setObjectAuthSup(List<ObjectEntity> objectAuthSup) {
        this.objectAuthSup = objectAuthSup;
    }
}
