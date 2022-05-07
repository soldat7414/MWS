package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.document_models.Document;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity(name = "document")
public class DocumentEntity {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String docType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private List<FileEntity> items;
    private Date createDate;
    private Date lastChangeDate;
    private String description;
    //links

    //license or else document have the person
    @ManyToOne
    @PrimaryKeyJoinColumn
    private PersonEntity person;
    //license or permissions have the organization
    @ManyToOne
    @PrimaryKeyJoinColumn
    private OrganizationEntity organization;
    //contract and additional agreements of the contract or permissions
    @ManyToOne
    @PrimaryKeyJoinColumn
    private ObjectEntity object;

    //item of correspondence
    // TODO: 06.05.2022

    //toModel
    public Document toModel (){
        return Document.toModel(this);
    }

    //constructors
    public DocumentEntity() {
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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public List<FileEntity> getItems() {
        return items;
    }

    public void setItems(List<FileEntity> items) {
        this.items = items;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public ObjectEntity getObject() {
        return object;
    }

    public void setObject(ObjectEntity object) {
        this.object = object;
    }
}

