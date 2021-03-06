package com.Soldat.MWS.entity;

import com.Soldat.MWS.entity.models.document_models.Document;
import com.Soldat.MWS.entity.supporting_classes.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity(name = "document")
public class DocumentEntity {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String whatDocument;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private List<FileEntity> items;
    private Date createDate;
    private Date lastChangeDate;
    private String description;
    //from what account was created organization
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
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

    public String getWhatDocument() {
        return whatDocument;
    }

    public void setWhatDocument(String whatDocument) {
        this.whatDocument = whatDocument;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

