package com.Soldat.MWS.entity.models.organization_models;

import com.Soldat.MWS.entity.OrganizationEntity;
import com.Soldat.MWS.entity.models.address_models.Address;
import com.Soldat.MWS.entity.models.contact_models.Contact;
import com.Soldat.MWS.entity.models.document_models.Document;
import com.Soldat.MWS.entity.models.person_models.Person;
import com.Soldat.MWS.entity.models.person_models.PersonWithoutLinks;
import com.Soldat.MWS.entity.supporting_classes.User;

import java.util.List;
import java.util.stream.Collectors;

public class Organization{
    //fields
    private long id;
    private String title;
    private String contrParty;
    //links
    private List<Person> employees;
//    private List<Object> objects;
    private List<Document> licenses;
//    private List<Object> objectCustomer;
//    private List<Correspondence> correspondenceFrom;
//    private Correspondence correspondenceTo;
    private Address address;
    private List<Contact> contacts;
    private User author;

    //to model
    public static Organization toModel (OrganizationEntity orgEntity){
        Organization organization = new Organization();
        organization.setId(orgEntity.getId());
        organization.setTitle(orgEntity.getTitle());
        if(orgEntity.getContrParty()!=null)organization.setContrParty(orgEntity.getContrParty());
        if(orgEntity.getEmployees()!=null)organization.setEmployees(PersonWithoutLinks.toModelList(orgEntity.getEmployees()));
        if(orgEntity.getLicenses()!=null)organization.setLicenses(Document.toModelList(orgEntity.getLicenses()));
//        if(organization.getObjects()!=null)organization.setObjects(new ToModelList<Object, ObjectEntity>().parse(orgEntity.getObjects()));
//        if(organization.getDocuments()!=null)organization.setDocuments(new ToModelList<Document, DocumentEntity>().parse(orgEntity.getDocuments()));
//        if(organization.getObjectCustomer()!=null)organization.setObjectCustomer(new ToModelList<Object, ObjectEntity>().parse(orgEntity.getObjectCustomer()));
//        if(organization.getCorrespondenceFrom()!=null)organization.setCorrespondenceFrom(new ToModelList<Correspondence, CorrespondenceEntity>().parse(orgEntity.getCorrespondenceFrom()));
//        if(organization.getCorrespondenceTo()!=null)organization.setCorrespondenceTo(new Correspondence().toModel(orgEntity.getCorrespondenceTo()));
        if(orgEntity.getAddress()!=null)organization.setAddress(orgEntity.getAddress().toModel());
        if(orgEntity.getContacts()!=null)organization.setContacts(Contact.toModelList(orgEntity.getContacts()));
        if(orgEntity.getAuthor()!=null)organization.setAuthor(orgEntity.getAuthor());
        return organization;
    }

    public static List<Organization> toModelList(List<OrganizationEntity> organizations){
        return organizations.stream().map(Organization::toModel).collect(Collectors.toList());
    }

    //constructor
    public Organization() {
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

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Document> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<Document> licenses) {
        this.licenses = licenses;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
